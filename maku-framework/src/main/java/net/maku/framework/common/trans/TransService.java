package net.maku.framework.common.trans;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

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
}
