package net.maku.framework.common.utils;

/**
 * Redis Key管理
 *
 * @author 阿沐 babamu@126.com
 */
public class RedisKeys {

    /**
     * 验证码Key
     */
    public static String getCaptchaKey(String key) {
        return "sys:captcha:" + key;
    }

    /**
     * 授权码Key
     */
    public static String getOauthCode(String code) {
        return "oauth:code:" + code;
    }

    /**
     * 微信小程序授权key
     *
     * @param key
     * @return
     */
    public static String getWxMpAuthKey(String key) {
        return "wx:mp:auth:" + key;
    }

    /**
     * 微信小程序授权类型key
     *
     * @param key
     * @return
     */
    public static String getWxMpAuthTypeKey(String key) {
        return "wx:mp:auth:type:" + key;
    }

}
