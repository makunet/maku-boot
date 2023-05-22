package net.maku.monitor.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.maku.framework.common.cache.RedisCache;
import net.maku.framework.common.cache.RedisKeys;
import net.maku.framework.common.query.Query;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.common.utils.Result;
import net.maku.framework.security.cache.TokenStoreCache;
import net.maku.framework.security.user.UserDetail;
import net.maku.monitor.vo.UserOnlineVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("monitor/user")
@AllArgsConstructor
@Tag(name = "在线用户监控")
public class UserOnlineController {
    private final TokenStoreCache tokenStoreCache;
    private final RedisCache redisCache;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('monitor:user:all')")
    public Result<PageResult<UserOnlineVO>> page(@ParameterObject @Valid Query query) {
        // 获取登录用户的全部key
        List<String> keys = tokenStoreCache.getUserKeyList();

        // 逻辑分页
        List<String> keyList = ListUtil.page(query.getPage() - 1, query.getLimit(), keys);

        List<UserOnlineVO> userOnlineList = new ArrayList<>();
        keyList.forEach(key -> {
            UserDetail user = (UserDetail) redisCache.get(key);
            if (user != null) {
                UserOnlineVO userOnlineVO = new UserOnlineVO();
                userOnlineVO.setId(user.getId());
                userOnlineVO.setUsername(user.getUsername());
                userOnlineVO.setRealName(user.getRealName());
                userOnlineVO.setGender(user.getGender());
                userOnlineVO.setEmail(user.getEmail());
                userOnlineVO.setAccessToken(key.replace(RedisKeys.getAccessTokenKey(""), ""));

                userOnlineList.add(userOnlineVO);
            }

        });

        return Result.ok(new PageResult<>(userOnlineList, keys.size()));
    }

    @DeleteMapping("{accessToken}")
    @Operation(summary = "强制退出")
    @PreAuthorize("hasAuthority('monitor:user:user')")
    public Result<String> forceLogout(@PathVariable("accessToken") String accessToken) {
        // token不能为空
        if (StrUtil.isBlank(accessToken)) {
            Result.error("token不能为空");
        }

        // 删除用户信息
        tokenStoreCache.deleteUser(accessToken);

        return Result.ok();
    }
}
