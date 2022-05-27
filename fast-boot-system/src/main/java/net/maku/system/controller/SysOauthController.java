package net.maku.system.controller;

import cn.hutool.core.lang.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.Result;
import net.maku.security.service.CaptchaService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证管理
 *
 * @author 阿沐 babamu@126.com
 */
@RestController
@RequestMapping("sys/oauth")
@Tag(name="认证管理")
@AllArgsConstructor
public class SysOauthController {
    private final CaptchaService captchaService;
    private final TokenStore tokenStore;

    @GetMapping("captcha")
    @Operation(summary = "验证码")
    public Result<Map<String, Object>> captcha() {
        // 生成key
        String key = UUID.randomUUID().toString();
        // 生成base64验证码
        String image = captchaService.generate(key);

        // 封装返回数据
        Map<String, Object> data = new HashMap<>();
        data.put("key", key);
        data.put("image", image);

        return Result.ok(data);
    }

    @PostMapping("logout")
    @Operation(summary = "退出")
    public Result<String> logout(HttpServletRequest request) {
        String access_token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
        if (oAuth2AccessToken != null) {
            tokenStore.removeAccessToken(oAuth2AccessToken);
            OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();
            tokenStore.removeRefreshToken(oAuth2RefreshToken);
            tokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
        }

        return Result.ok();
    }
}
