package net.maku.framework.security.third;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * 第三方登录 AuthenticationProvider
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public class ThirdAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    private final ThirdUserDetailsService thirdUserDetailsService;
    private final ThirdOpenIdService thirdOpenIdService;

    public ThirdAuthenticationProvider(ThirdUserDetailsService thirdUserDetailsService, ThirdOpenIdService thirdOpenIdService) {
        this.thirdUserDetailsService = thirdUserDetailsService;
        this.thirdOpenIdService = thirdOpenIdService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(ThirdAuthenticationToken.class, authentication,
                () -> messages.getMessage(
                        "ThirdAuthenticationProvider.onlySupports",
                        "Only ThirdAuthenticationProvider is supported"));

        ThirdAuthenticationToken authenticationToken = (ThirdAuthenticationToken) authentication;
        ThirdLogin login = (ThirdLogin) authenticationToken.getPrincipal();

        try {
            // 获取用户 openId
            String openId = thirdOpenIdService.getOpenId(login);
            // 获取用户信息
            UserDetails userDetails = thirdUserDetailsService.loadUserByOpenTypeAndOpenId(login.getOpenType(), openId);
            if (userDetails == null) {
                throw new BadCredentialsException("Bad credentials");
            }

            return createSuccessAuthentication(authentication, userDetails);
        } catch (UsernameNotFoundException ex) {
            throw new BadCredentialsException(this.messages
                    .getMessage("ThirdAuthenticationProvider.badCredentials", "Bad credentials"));
        }

    }

    protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
        ThirdAuthenticationToken result = new ThirdAuthenticationToken(user,
                authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ThirdAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(thirdUserDetailsService, "thirdUserDetailsService must not be null");
        Assert.notNull(thirdOpenIdService, "thirdOpenIdService must not be null");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

}
