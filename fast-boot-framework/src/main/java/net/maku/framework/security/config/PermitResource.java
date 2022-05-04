package net.maku.framework.security.config;

/**
 * 允许访问的资源
 *
 * @author 阿沐 babamu@126.com
 */
public class PermitResource {
    /**
     * 指定被 spring security oauth2.0 忽略的URL
     */
    public static final String [] IGNORING_URLS = {
        "/actuator/**",
        "/v3/api-docs/**",
        "/webjars/**",
        "/swagger/**",
        "/swagger-resources/**",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/doc.html",
        "/oauth/captcha"
    };
}
