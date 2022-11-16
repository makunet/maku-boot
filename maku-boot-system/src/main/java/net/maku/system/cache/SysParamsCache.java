package net.maku.system.cache;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import net.maku.framework.common.cache.RedisCache;
import net.maku.system.entity.SysParamsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 参数管理 Cache
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class SysParamsCache {
    private final RedisCache redisCache;

    /**
     * 参数管理 KEY
     */
    private final String SYSTEM_PARAMS_KEY = "system:params";

    /**
     * 保存参数列表到缓存
     *
     * @param list 参数列表
     */
    public void saveList(List<SysParamsEntity> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        
        // list 转成 map
        Map<String, Object> map = list.stream().collect(Collectors.toMap(SysParamsEntity::getParamKey, SysParamsEntity::getParamValue));

        redisCache.hMSet(SYSTEM_PARAMS_KEY, map, RedisCache.NOT_EXPIRE);
    }

    /**
     * 删除缓存中的全部参数列表
     */
    public void delList() {
        redisCache.delete(SYSTEM_PARAMS_KEY);
    }

    /**
     * 根据参数键，获取参数值
     *
     * @param paramKey 参数键
     */
    public String get(String paramKey) {
        return (String) redisCache.hGet(SYSTEM_PARAMS_KEY, paramKey);
    }

    /**
     * 根据参数键，获取参数值
     *
     * @param paramKey   参数键
     * @param paramValue 参数值
     */
    public void save(String paramKey, String paramValue) {
        redisCache.hSet(SYSTEM_PARAMS_KEY, paramKey, paramValue);
    }

    /**
     * 根据参数键，删除参数值
     *
     * @param paramKey 参数键
     */
    public void del(String... paramKey) {
        redisCache.hDel(SYSTEM_PARAMS_KEY, paramKey);
    }
}
