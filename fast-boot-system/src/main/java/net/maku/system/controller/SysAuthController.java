package net.maku.system.controller;

import cn.hutool.core.lang.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.maku.framework.common.constant.Constant;
import net.maku.framework.common.utils.Result;
import net.maku.framework.security.cache.TokenStoreCache;
import net.maku.framework.security.user.UserDetail;
import net.maku.framework.security.utils.TokenUtils;
import net.maku.system.enums.LoginOperationEnum;
import net.maku.system.service.SysCaptchaService;
import net.maku.system.service.SysLogLoginService;
import net.maku.system.vo.SysLoginVO;
import net.maku.system.vo.SysTokenVO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证管理
 *
 * @author 阿沐 babamu@126.com
 */
@RestController
@RequestMapping("sys/auth")
@Tag(name = "认证管理")
@AllArgsConstructor
public class SysAuthController {
    private final SysCaptchaService sysCaptchaService;
    private final TokenStoreCache tokenStoreCache;
    private final AuthenticationManager authenticationManager;
    private final SysLogLoginService sysLogLoginService;

    @GetMapping("captcha")
    @Operation(summary = "验证码")
    public Result<Map<String, Object>> captcha() {
        // 生成key
        String key = UUID.randomUUID().toString();
        // 生成base64验证码
        String image = sysCaptchaService.generate(key);

        // 封装返回数据
        Map<String, Object> data = new HashMap<>();
        data.put("key", key);
        data.put("image", image);

        return Result.ok(data);
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public Result<SysTokenVO> login(@RequestBody SysLoginVO login) {
        // 验证码效验
        boolean flag = sysCaptchaService.validate(login.getKey(), login.getCaptcha());
        if (!flag) {
            // 保存登录日志
            sysLogLoginService.save(login.getUsername(), Constant.FAIL, LoginOperationEnum.CAPTCHA_FAIL.getValue());

            return Result.error("验证码错误");
        }

        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (BadCredentialsException e) {
            return Result.error("用户名密码错误");
        }

        // 用户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();

        // 生成 accessToken
        String accessToken = TokenUtils.generator();

        // 保存用户信息到缓存
        tokenStoreCache.saveUser(accessToken, user);

        return Result.ok(new SysTokenVO(accessToken));
    }

    @PostMapping("logout")
    @Operation(summary = "退出")
    public Result<String> logout(HttpServletRequest request) {
        String accessToken = TokenUtils.getAccessToken(request);

        // 用户信息
        UserDetail user = tokenStoreCache.getUser(accessToken);

        // 删除用户信息
        tokenStoreCache.deleteUser(accessToken);

        // 保存登录日志
        sysLogLoginService.save(user.getUsername(), Constant.SUCCESS, LoginOperationEnum.LOGOUT_SUCCESS.getValue());

        return Result.ok();
    }
}
