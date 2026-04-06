package net.maku.framework.common.xss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import tools.jackson.databind.module.SimpleModule;

/**
 * XSS 配置文件
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Configuration
@EnableConfigurationProperties(XssProperties.class)
@ConditionalOnProperty(prefix = "maku.xss", value = "enabled")
public class XssConfiguration {
    private final static PathMatcher pathMatcher = new AntPathMatcher();

    @Bean
    public FilterRegistrationBean<XssFilter> xssFilter(XssProperties properties) {
        FilterRegistrationBean<XssFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new XssFilter(properties, pathMatcher));
        bean.setOrder(Integer.MAX_VALUE);
        bean.setName("xssFilter");

        return bean;
    }

    /**
     * xss过滤模块，处理json类型的请求
     * Spring Boot 4 会自动将 Module Bean 注册到 JsonMapper，无需手动创建 ObjectMapper
     */
    @Bean
    public SimpleModule xssFilterModule(XssProperties properties) {
        SimpleModule module = new SimpleModule("XssFilterJsonDeserializer");
        module.addDeserializer(String.class, new XssFilterJsonDeserializer(properties, pathMatcher));
        return module;
    }
}
