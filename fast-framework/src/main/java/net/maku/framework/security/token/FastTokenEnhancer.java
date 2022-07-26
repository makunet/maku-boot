package net.maku.framework.security.token;

import net.maku.framework.common.utils.Result;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 令牌
 *
 * @author 阿沐 babamu@126.com
 */
public class FastTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if(accessToken instanceof DefaultOAuth2AccessToken){
            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;

            // 增加额外信息
            Map<String, Object> info = new HashMap<>();
            info.put("code", new Result<>().getCode());
            token.setAdditionalInformation(info);

            return token;
        }

        return accessToken;
    }
}