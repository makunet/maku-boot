package net.maku.message.cache;

import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.RedisUtils;
import net.maku.message.sms.config.SmsConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  短信平台 Cache
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class SmsPlatformCache {
    private final RedisUtils redisUtils;

    /**
     * 短信平台轮询KEY
     */
    private final String SMS_ROUND_KEY = "message:sms:round";

    /**
     * 短信平台列表KEY
     */
    private final String SMS_PLATFORM_KEY = "message:sms:platform";

    /**
     * 获取短信轮询值
     */
    public Long getRoundValue() {
        return redisUtils.increment(SMS_ROUND_KEY);
    }

    public List<SmsConfig> list(){
        return (List<SmsConfig>)redisUtils.get(SMS_PLATFORM_KEY);
    }

    public void save(List<SmsConfig> list){
        redisUtils.set(SMS_PLATFORM_KEY, list);
    }

    public void delete(){
        redisUtils.delete(SMS_PLATFORM_KEY);
    }
}
