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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局翻译拦截器
 * <p>
 * 只拦截返回值中包含 TransPojo 的 ResponseBody，自动对实现了 TransPojo 接口的对象进行字段翻译
 *
 * @author maku
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class TransResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private final TransService transService;

    /**
     * 缓存方法返回类型是否涉及 TransPojo 的判断结果，避免重复反射
     */
    private final Map<MethodParameter, Boolean> supportsCache = new ConcurrentHashMap<>();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return supportsCache.computeIfAbsent(returnType, this::containsTransPojo);
    }

    /**
     * 判断方法返回类型是否直接或间接包含 TransPojo
     */
    private boolean containsTransPojo(MethodParameter returnType) {
        Type genericType = returnType.getGenericParameterType();
        return checkType(genericType, new HashSet<>());
    }

    /**
     * 递归检查类型及其泛型参数是否涉及 TransPojo
     */
    private boolean checkType(Type type, Set<Type> visited) {
        if (type == null || !visited.add(type)) {
            return false;
        }

        // 处理 Class 类型
        if (type instanceof Class<?> clazz) {
            if (TransPojo.class.isAssignableFrom(clazz)) {
                return true;
            }
            // 检查类的字段中是否包含 TransPojo 类型（仅检查非 JDK 类）
            if (!clazz.getName().startsWith("java.") && !clazz.getName().startsWith("javax.")
                    && !clazz.isPrimitive() && !clazz.isEnum()) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (checkType(field.getGenericType(), visited)) {
                        return true;
                    }
                }
            }
            return false;
        }

        // 处理泛型参数类型，如 Result<PageResult<UserVO>>
        if (type instanceof ParameterizedType parameterizedType) {
            Type rawType = parameterizedType.getRawType();
            if (rawType instanceof Class<?> rawClass && TransPojo.class.isAssignableFrom(rawClass)) {
                return true;
            }
            for (Type arg : parameterizedType.getActualTypeArguments()) {
                if (checkType(arg, visited)) {
                    return true;
                }
            }
            // 同时检查原始类型的字段
            if (rawType instanceof Class<?>) {
                return checkType(rawType, visited);
            }
        }

        return false;
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
