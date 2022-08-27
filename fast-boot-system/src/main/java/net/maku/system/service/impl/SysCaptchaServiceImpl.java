package net.maku.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import net.maku.framework.common.cache.RedisCache;
import net.maku.framework.common.cache.RedisKeys;
import net.maku.system.service.SysCaptchaService;
import org.springframework.stereotype.Service;

/**
 * 验证码
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class SysCaptchaServiceImpl implements SysCaptchaService {
    private final RedisCache redisCache;

    @Override
    public String generate(String key) {
        // 生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        // 保存到缓存
        key = RedisKeys.getCaptchaKey(key);
        redisCache.set(key, captcha.text(), 300);

        return captcha.toBase64();
    }

    @Override
    public boolean validate(String key, String code) {
        if (StrUtil.isBlank(key) || StrUtil.isBlank(code)) {
            return false;
        }

        // 获取验证码
        String captcha = getCache(key);

        // 效验成功
        if (code.equalsIgnoreCase(captcha)) {
            return true;
        }

        return false;
    }

    private String getCache(String key) {
        key = RedisKeys.getCaptchaKey(key);
        String captcha = (String) redisCache.get(key);
        // 删除验证码
        if (captcha != null) {
            redisCache.delete(key);
        }

        return captcha;
    }
}
