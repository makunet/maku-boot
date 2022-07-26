package net.maku.framework.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误编码
 *
 * @author 阿沐 babamu@126.com
*/
@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(401, "还未授权，不能访问"),
    FORBIDDEN(403, "没有权限，禁止访问"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后再试"),
    ACCOUNT_PASSWORD_ERROR(1001, "账号或密码错误");

    private final int code;
    private final String msg;
}
