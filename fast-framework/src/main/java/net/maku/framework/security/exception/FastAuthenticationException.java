package net.maku.framework.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 认证异常类
 *
 * @author 阿沐 babamu@126.com
 */
public class FastAuthenticationException extends AuthenticationException {
    public FastAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public FastAuthenticationException(String msg) {
        super(msg);
    }
}
