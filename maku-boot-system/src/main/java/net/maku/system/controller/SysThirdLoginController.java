package net.maku.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import net.maku.framework.common.utils.Result;
import net.maku.framework.operatelog.annotations.OperateLog;
import net.maku.framework.operatelog.enums.OperateTypeEnum;
import net.maku.framework.security.user.SecurityUser;
import net.maku.system.service.SysThirdLoginConfigService;
import net.maku.system.service.SysThirdLoginService;
import net.maku.system.vo.SysThirdCallbackVO;
import net.maku.system.vo.SysThirdLoginVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第三方账号登录
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@RestController
@RequestMapping("sys/third")
@Tag(name = "第三方账号")
@AllArgsConstructor
public class SysThirdLoginController {
    private final SysThirdLoginService sysThirdLoginService;
    private final SysThirdLoginConfigService sysThirdLoginConfigService;

    @GetMapping("list")
    @Operation(summary = "列表")
    public Result<List<SysThirdLoginVO>> list() {
        List<SysThirdLoginVO> list = sysThirdLoginService.listByUserId(SecurityUser.getUserId());

        return Result.ok(list);
    }

    @RequestMapping("render/{source}")
    public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = sysThirdLoginConfigService.getAuthRequest(source);
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
        response.sendRedirect(authorizeUrl);
    }

    @RequestMapping("/callback/{source}")
    public ModelAndView login(@PathVariable("source") String source, AuthCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("openType", source);
        map.put("state", callback.getState());
        map.put("code", callback.getCode());

        return new ModelAndView("third_login", map);
    }

    @PostMapping("bind")
    @Operation(summary = "绑定")
    @OperateLog(type = OperateTypeEnum.INSERT)
    public Result<String> bind(@RequestBody SysThirdCallbackVO vo) {
        AuthRequest authRequest = sysThirdLoginConfigService.getAuthRequest(vo.getOpenType());
        AuthCallback callback = AuthCallback.builder().code(vo.getCode()).state(vo.getState()).build();

        // 根据code，获取用户信息
        AuthResponse<AuthUser> response = authRequest.login(callback);

        // 判断是否成功
        if (!response.ok()) {
            throw new RuntimeException("第三方登录失败");
        }

        // 绑定用户信息
        sysThirdLoginService.bind(SecurityUser.getUserId(), vo.getOpenType(), response.getData());

        return Result.ok();
    }

    @PutMapping("unbind/{openType}")
    @Operation(summary = "解绑")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    public Result<String> unBind(@PathVariable("openType") String openType) {
        sysThirdLoginService.unBind(SecurityUser.getUserId(), openType);

        return Result.ok();
    }
}
