package net.maku.framework.common.trans;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 翻译服务
 * <p>
 * 提供手动批量翻译能力，用于导出Excel等需要主动翻译的场景
 *
 * @author maku
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TransService {
    private final DictionaryTransService dictionaryTransService;

    /**
     * 批量翻译
     *
     * @param dataList 需要翻译的数据列表
     */
    public <T> void transBatch(List<T> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        for (T data : dataList) {
            trans(data);
        }
    }

    /**
     * 单个对象翻译
     *
     * @param data 需要翻译的对象
     */
    public void trans(Object data) {
        if (data == null) {
            return;
        }

        Class<?> clazz = data.getClass();
        List<Field> fields = TransReflectUtils.getAnnotationField(clazz, Trans.class);

        for (Field field : fields) {
            try {
                Trans trans = field.getAnnotation(Trans.class);
                if (trans == null) {
                    continue;
                }

                if (TransType.DICTIONARY.equals(trans.type())) {
                    translateDictionary(data, clazz, field, trans);
                } else if (TransType.SIMPLE.equals(trans.type())) {
                    translateSimple(data, clazz, field, trans);
                }
                // 可以在此扩展其他翻译类型
            } catch (Exception e) {
                log.debug("翻译异常: field={}", field.getName(), e);
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
    @SuppressWarnings({"rawtypes"})
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

    private final Map<Class<?>, BaseMapper<?>> mapperCache = new ConcurrentHashMap<>();

    @SuppressWarnings("rawtypes")
    private BaseMapper getMapper(Class<?> entityClass) {
        return mapperCache.computeIfAbsent(entityClass, c -> {
            try {
                Map<String, BaseMapper> mappers = SpringUtil.getBeansOfType(BaseMapper.class);
                for (BaseMapper mapper : mappers.values()) {
                    try {
                        String entityName = c.getSimpleName().replace("Entity", "");
                        for (Class<?> intf : mapper.getClass().getInterfaces()) {
                            if (intf.getSimpleName().contains(entityName)) {
                                return mapper;
                            }
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
