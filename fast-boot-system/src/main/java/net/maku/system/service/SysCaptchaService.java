package net.maku.system.service;

/**
 * 验证码
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysCaptchaService {
    /**
     * 生成验证码
     *
     * @param key key
     * @return 返回base64图片验证码
     */
    String generate(String key);

    /**
     * 验证码效验
     *
     * @param key  key
     * @param code 验证码
     * @return true：成功  false：失败
     */
    boolean validate(String key, String code);
}
