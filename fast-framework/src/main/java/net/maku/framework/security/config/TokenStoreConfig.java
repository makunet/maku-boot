package net.maku.framework.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * TokenStore
 *
 * @author 阿沐 babamu@126.com
 */
@Configuration
@AllArgsConstructor
public class TokenStoreConfig {
    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore tokenStore() {
        // 使用redis存储token
        return new RedisTokenStore(redisConnectionFactory);
    }
}
