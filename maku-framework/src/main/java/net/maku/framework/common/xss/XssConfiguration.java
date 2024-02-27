package net.maku.framework.common.xss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

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
     * xss过滤，处理json类型的请求
     */
    @Bean
    public ObjectMapper xssFilterObjectMapper(Jackson2ObjectMapperBuilder builder, XssProperties properties) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 注册xss过滤器
        SimpleModule module = new SimpleModule("XssFilterJsonDeserializer");
        module.addDeserializer(String.class, new XssFilterJsonDeserializer(properties, pathMatcher));
        objectMapper.registerModule(module);

        return objectMapper;
    }
}
