package net.maku.framework.common.trans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字典翻译服务
 * <p>
 * 维护字典翻译缓存，提供正向翻译（value -> label）和反向翻译（label -> value）
 *
 * @author maku
 */
@Slf4j
@Service
public class DictionaryTransService {
    /**
     * 字典翻译缓存
     * key 格式: dictType_dictValue -> dictLabel (正向)
     * key 格式: un_trans:dictType_dictLabel -> dictValue (反向)
     */
    private final Map<String, String> dictionaryTransMap = new ConcurrentHashMap<>();

    /**
     * 获取字典翻译Map
     *
     * @return 翻译缓存
     */
    public Map<String, String> getDictionaryTransMap() {
        return dictionaryTransMap;
    }

    /**
     * 刷新字典缓存
     *
     * @param dictType 字典类型
     * @param dataMap  字典数据 key: dictValue, value: dictLabel
     */
    public void refreshCache(String dictType, Map<String, String> dataMap) {
        if (dictType == null || dataMap == null) {
            return;
        }
        // 先清除该字典类型的旧缓存
        dictionaryTransMap.entrySet().removeIf(entry ->
                entry.getKey().startsWith(dictType + "_") ||
                        entry.getKey().startsWith("un_trans:" + dictType + "_"));
        // 添加新缓存
        dataMap.forEach((value, label) -> {
            // 正向翻译: dictType_value -> label
            dictionaryTransMap.put(dictType + "_" + value, label);
            // 反向翻译: un_trans:dictType_label -> value
            dictionaryTransMap.put("un_trans:" + dictType + "_" + label, value);
        });
    }

    /**
     * 正向翻译：根据字典类型和字典值获取字典标签
     *
     * @param dictType  字典类型
     * @param dictValue 字典值
     * @return 字典标签
     */
    public String translate(String dictType, Object dictValue) {
        if (dictType == null || dictValue == null) {
            return null;
        }
        return dictionaryTransMap.get(dictType + "_" + dictValue);
    }

    /**
     * 反向翻译：根据字典类型和字典标签获取字典值
     *
     * @param dictType  字典类型
     * @param dictLabel 字典标签
     * @return 字典值
     */
    public String reverseTranslate(String dictType, String dictLabel) {
        if (dictType == null || dictLabel == null) {
            return null;
        }
        return dictionaryTransMap.get("un_trans:" + dictType + "_" + dictLabel);
    }
}
