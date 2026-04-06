package net.maku.framework.common.trans;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final DictionaryTransService dictionaryTransService;

    /**
     * 缓存 SimpleTranslation 的 mapper，避免反复查找
     */
    private final Map<Class<?>, BaseMapper<?>> mapperCache = new HashMap<>();

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

        translateFields(obj);
    }

    /**
     * 处理包装类中可能包含 TransPojo 的字段
     */
    private void processWrapperFields(Object obj) {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                Class<?> fieldType = field.getType();
                if (Collection.class.isAssignableFrom(fieldType)) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (value instanceof Collection<?> collection) {
                        for (Object item : collection) {
                            if (item instanceof TransPojo) {
                                translateFields(item);
                            }
                        }
                    }
                } else if (TransPojo.class.isAssignableFrom(fieldType)) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (value != null) {
                        translateFields(value);
                    }
                }
            } catch (Exception e) {
                // ignore
            }
        }
    }

    /**
     * 对对象进行翻译
     */
    private void translateFields(Object obj) {
        Class<?> clazz = obj.getClass();
        List<Field> fields = TransReflectUtils.getAnnotationField(clazz, Trans.class);

        for (Field field : fields) {
            try {
                Trans trans = field.getAnnotation(Trans.class);
                if (trans == null) {
                    continue;
                }

                switch (trans.type()) {
                    case TransType.DICTIONARY -> translateDictionary(obj, clazz, field, trans);
                    case TransType.SIMPLE -> translateSimple(obj, clazz, field, trans);
                    default -> log.debug("未知的翻译类型: {}", trans.type());
                }
            } catch (Exception e) {
                log.debug("字段翻译异常: field={}", field.getName(), e);
            }
        }
    }

    /**
     * 字典翻译
     */
    private void translateDictionary(Object obj, Class<?> clazz, Field field, Trans trans) throws Exception {
        if (trans.key().isEmpty() || trans.ref().isEmpty()) {
            return;
        }

        field.setAccessible(true);
        Object fieldValue = field.get(obj);
        if (fieldValue == null) {
            return;
        }

        String label = dictionaryTransService.translate(trans.key(), fieldValue);
        if (label != null) {
            Field refField = TransReflectUtils.getDeclaredField(clazz, trans.ref());
            if (refField != null) {
                refField.setAccessible(true);
                refField.set(obj, label);
            }
        }
    }

    /**
     * 简单翻译（通过实体类查找）
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void translateSimple(Object obj, Class<?> clazz, Field field, Trans trans) throws Exception {
        if (trans.target() == Object.class || trans.fields().isEmpty() || trans.ref().isEmpty()) {
            return;
        }

        field.setAccessible(true);
        Object fieldValue = field.get(obj);
        if (fieldValue == null) {
            return;
        }

        try {
            // 获取目标实体对应的 Mapper
            BaseMapper mapper = getMapper(trans.target());
            if (mapper == null) {
                return;
            }

            // 查询目标实体
            Object targetEntity = mapper.selectById((java.io.Serializable) fieldValue);
            if (targetEntity == null) {
                return;
            }

            // 获取目标字段的值
            Field targetField = TransReflectUtils.getDeclaredField(trans.target(), trans.fields());
            if (targetField == null) {
                return;
            }
            targetField.setAccessible(true);
            Object targetValue = targetField.get(targetEntity);

            // 设置到 ref 字段
            Field refField = TransReflectUtils.getDeclaredField(clazz, trans.ref());
            if (refField != null) {
                refField.setAccessible(true);
                refField.set(obj, targetValue);
            }
        } catch (Exception e) {
            log.debug("Simple翻译异常: target={}, field={}", trans.target().getSimpleName(), field.getName(), e);
        }
    }

    /**
     * 获取目标实体对应的 BaseMapper
     */
    @SuppressWarnings("rawtypes")
    private BaseMapper getMapper(Class<?> entityClass) {
        return mapperCache.computeIfAbsent(entityClass, clazz -> {
            try {
                Map<String, BaseMapper> mappers = SpringUtil.getBeansOfType(BaseMapper.class);
                for (BaseMapper mapper : mappers.values()) {
                    try {
                        // 尝试通过 Wrappers 来推断 mapper 对应的实体类
                        // 这里简单通过类名匹配
                        String mapperClassName = mapper.getClass().getInterfaces()[0].getSimpleName();
                        String entityName = clazz.getSimpleName().replace("Entity", "");
                        if (mapperClassName.contains(entityName)) {
                            return mapper;
                        }
                    } catch (Exception e) {
                        // ignore
                    }
                }
            } catch (Exception e) {
                log.debug("获取Mapper异常: entityClass={}", entityClass.getSimpleName(), e);
            }
            return null;
        });
    }
}
