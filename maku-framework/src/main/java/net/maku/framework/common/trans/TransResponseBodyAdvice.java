package net.maku.framework.common.trans;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * 全局翻译拦截器
 * <p>
 * 拦截所有 ResponseBody 返回值，自动对实现了 TransPojo 接口的对象进行字段翻译
 *
 * @author maku
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class TransResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private final TransService transService;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return null;
        }
        try {
            processTranslation(body);
        } catch (Exception e) {
            log.debug("翻译处理异常", e);
        }
        return body;
    }

    private void processTranslation(Object obj) {
        if (obj == null) {
            return;
        }

        if (obj instanceof Collection<?> collection) {
            for (Object item : collection) {
                processTranslation(item);
            }
            return;
        }

        // 处理常见的包装类型（如 PageResult、Result 等）
        processWrapperFields(obj);

        if (!(obj instanceof TransPojo)) {
            return;
        }

        transService.trans(obj);
    }

    /**
     * 处理包装类中可能包含 TransPojo 的字段
     */
    private void processWrapperFields(Object obj) {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value == null) {
                    continue;
                }

                if (value instanceof Collection<?> collection) {
                    for (Object item : collection) {
                        if (item instanceof TransPojo) {
                            transService.trans(item);
                        }
                    }
                } else if (value instanceof TransPojo) {
                    transService.trans(value);
                } else if (!value.getClass().getName().startsWith("java.") && !value.getClass().getName().startsWith("javax.")) {
                    processWrapperFields(value);
                }
            } catch (Exception e) {
                // ignore
            }
        }
    }
}
