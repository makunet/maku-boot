package net.maku.security.service;

import cn.hutool.core.util.StrUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.RedisKeys;
import net.maku.framework.common.utils.RedisUtils;
import org.springframework.stereotype.Service;

/**
 * 验证码
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class CaptchaService {
    private final RedisUtils redisUtils;

    /**
     * 生成验证码
     * @param key    key
     * @return      返回base64图片验证码
     */
    public String generate(String key) {
        // 生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        // 保存到缓存
        key = RedisKeys.getCaptchaKey(key);
        redisUtils.set(key, captcha.text(), 300);

        return captcha.toBase64();
    }

    /**
     * 验证码效验
     * @param key  key
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    public boolean validate(String key, String code) {
        if(StrUtil.isBlank(key) || StrUtil.isBlank(code)){
            return false;
        }

        // 获取验证码
        String captcha = getCache(key);

        // 效验成功
        if(code.equalsIgnoreCase(captcha)){
            return true;
        }

        return false;
    }


    private String getCache(String key){
        key = RedisKeys.getCaptchaKey(key);
        String captcha = (String)redisUtils.get(key);
        // 删除验证码
        if(captcha != null){
            redisUtils.delete(key);
        }

        return captcha;
    }
}