package net.maku.security.service;

import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.RedisKeys;
import net.maku.framework.common.utils.RedisUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

/**
 * 基于Redis的授权码模式
 * 
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {
	private final RedisUtils redisUtils;

	@Override
	protected void store(String code, OAuth2Authentication authentication) {
		String key = RedisKeys.getOauthCode(code);
		redisUtils.set(key, authentication);
	}

	@Override
	public OAuth2Authentication remove(String code) {
		String key = RedisKeys.getOauthCode(code);
		return (OAuth2Authentication)redisUtils.get(key);
	}

}