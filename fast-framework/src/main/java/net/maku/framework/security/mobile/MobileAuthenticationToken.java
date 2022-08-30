package net.maku.framework.security.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * 手机短信登录 AuthenticationToken
 *
 * @author 阿沐 babamu@126.com
 */
public class MobileAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private final Object principal;
    private String code;

    public MobileAuthenticationToken(Object principal, String code) {
        super(null);
        this.principal = principal;
        this.code = code;
        setAuthenticated(false);
    }

    public MobileAuthenticationToken(Object principal, String code, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.code = code;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return this.code;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.code = null;
    }
}
