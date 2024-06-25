package net.maku.system.cache;

import lombok.AllArgsConstructor;
import net.maku.email.config.EmailConfig;
import net.maku.framework.common.cache.RedisCache;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 邮件配置 Cache
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Service
@AllArgsConstructor
public class EmailConfigCache {
    private final RedisCache redisCache;

    /**
     * 邮件平台轮询KEY
     */
    private final String SMS_ROUND_KEY = "sys:mail:round";
    private final String SMS_ROUND_CODE_KEY = "sys:mail:round:code";

    /**
     * 邮件平台列表KEY
     */
    private final String SMS_PLATFORM_KEY = "sys:mail:platform";

    /**
     * 获取邮件轮询值
     */
    public Long getRoundValue() {
        return redisCache.increment(SMS_ROUND_KEY);
    }

    /**
     * 获取邮件编码轮询值
     */
    public Long getRoundCodeValue() {
        return redisCache.increment(SMS_ROUND_CODE_KEY);
    }

    public List<EmailConfig> list() {
        return (List<EmailConfig>) redisCache.get(SMS_PLATFORM_KEY);
    }

    public void save(List<EmailConfig> list) {
        redisCache.set(SMS_PLATFORM_KEY, list);
    }

    public void delete() {
        redisCache.delete(SMS_PLATFORM_KEY);
    }
}
