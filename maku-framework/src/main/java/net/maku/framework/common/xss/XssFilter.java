package net.maku.framework.common.xss;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Xss 过滤器
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@AllArgsConstructor
public class XssFilter extends OncePerRequestFilter {
    private final XssProperties properties;
    private final PathMatcher pathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        filterChain.doFilter(new XssRequestWrapper(request), response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 如果是json数据，则不处理
        String contentType = request.getContentType();
        if (StrUtil.isBlank(contentType) || StrUtil.startWithIgnoreCase(contentType, MediaType.APPLICATION_JSON_VALUE)) {
            return true;
        }

        // 放行不过滤的URL
        return properties.getExcludeUrls().stream().anyMatch(excludeUrl -> pathMatcher.match(excludeUrl, request.getServletPath()));
    }

}
