package net.maku.framework.security.mobile;

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
 * 手机短信登录 AuthenticationProvider
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public class MobileAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    private final MobileUserDetailsService mobileUserDetailsService;
    private final MobileVerifyCodeService mobileVerifyCodeService;

    public MobileAuthenticationProvider(MobileUserDetailsService mobileUserDetailsService, MobileVerifyCodeService mobileVerifyCodeService) {
        this.mobileUserDetailsService = mobileUserDetailsService;
        this.mobileVerifyCodeService = mobileVerifyCodeService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(MobileAuthenticationToken.class, authentication,
                () -> messages.getMessage(
                        "MobileAuthenticationProvider.onlySupports",
                        "Only MobileAuthenticationProvider is supported"));

        MobileAuthenticationToken authenticationToken = (MobileAuthenticationToken) authentication;
        String mobile = authenticationToken.getName();
        String code = (String) authenticationToken.getCredentials();

        try {
            UserDetails userDetails = mobileUserDetailsService.loadUserByMobile(mobile);
            if (userDetails == null) {
                throw new BadCredentialsException("Bad credentials");
            }

            // 短信验证码效验
            if (mobileVerifyCodeService.verifyCode(mobile, code)) {
                return createSuccessAuthentication(authentication, userDetails);
            } else {
                throw new BadCredentialsException("mobile code is not matched");
            }
        } catch (UsernameNotFoundException ex) {
            throw new BadCredentialsException(this.messages
                    .getMessage("MobileAuthenticationProvider.badCredentials", "Bad credentials"));
        }

    }

    protected Authentication createSuccessAuthentication(Authentication authentication, UserDetails user) {
        MobileAuthenticationToken result = new MobileAuthenticationToken(user, null,
                authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(mobileUserDetailsService, "mobileUserDetailsService must not be null");
        Assert.notNull(mobileVerifyCodeService, "mobileVerifyCodeService must not be null");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

}
