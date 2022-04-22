package net.maku.framework.security.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义异常
 *
 * @author 阿沐 babamu@126.com
 */
public class FastOAuth2Exception extends OAuth2Exception {
	private String msg;

	public FastOAuth2Exception(String msg) {
		super(msg);
		this.msg = msg;
	}

	public FastOAuth2Exception(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}