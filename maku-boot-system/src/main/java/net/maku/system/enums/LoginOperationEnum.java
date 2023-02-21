package net.maku.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录信息
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Getter
@AllArgsConstructor
public enum LoginOperationEnum {
    /**
     * 登录成功
     */
    LOGIN_SUCCESS(0),
    /**
     * 退出成功
     */
    LOGOUT_SUCCESS(1),
    /**
     * 验证码错误
     */
    CAPTCHA_FAIL(2),
    /**
     * 账号密码错误
     */
    ACCOUNT_FAIL(3);

    private final int value;
}
