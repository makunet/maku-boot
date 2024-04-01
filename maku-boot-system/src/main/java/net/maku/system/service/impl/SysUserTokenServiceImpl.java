package net.maku.system.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.maku.framework.common.exception.ErrorCode;
import net.maku.framework.common.exception.ServerException;
import net.maku.framework.mybatis.service.impl.BaseServiceImpl;
import net.maku.framework.security.cache.TokenStoreCache;
import net.maku.framework.security.properties.SecurityProperties;
import net.maku.framework.security.user.UserDetail;
import net.maku.framework.security.utils.TokenUtils;
import net.maku.system.convert.SysUserConvert;
import net.maku.system.convert.SysUserTokenConvert;
import net.maku.system.dao.SysUserDao;
import net.maku.system.dao.SysUserTokenDao;
import net.maku.system.entity.SysUserEntity;
import net.maku.system.entity.SysUserTokenEntity;
import net.maku.system.service.SysUserDetailsService;
import net.maku.system.service.SysUserTokenService;
import net.maku.system.vo.SysUserTokenVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 用户Token
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class SysUserTokenServiceImpl extends BaseServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
    private final TokenStoreCache tokenStoreCache;
    private final SysUserDetailsService sysUserDetailsService;
    private final SecurityProperties securityProperties;
    private final SysUserDao sysUserDao;

    @Override
    public SysUserTokenVO createToken(Long userId) {
        // 生成token
        String accessToken = TokenUtils.generator();
        String refreshToken = TokenUtils.generator();

        SysUserTokenEntity entity = new SysUserTokenEntity();
        entity.setUserId(userId);
        entity.setAccessToken(accessToken);
        entity.setRefreshToken(refreshToken);

        // 过期时间
        Date now = new Date();
        entity.setAccessTokenExpire(DateUtil.toLocalDateTime(DateUtil.offsetSecond(now, securityProperties.getAccessTokenExpire())));
        entity.setRefreshTokenExpire(DateUtil.toLocalDateTime(DateUtil.offsetSecond(now, securityProperties.getRefreshTokenExpire())));

        // 是否存在Token
        SysUserTokenEntity tokenEntity = baseMapper.selectOne(new LambdaQueryWrapper<SysUserTokenEntity>().eq(SysUserTokenEntity::getUserId, userId));
        if (tokenEntity == null) {
            baseMapper.insert(entity);
        } else {
            entity.setId(tokenEntity.getId());
            baseMapper.updateById(entity);
        }

        return SysUserTokenConvert.INSTANCE.convert(entity);
    }

    @Override
    public SysUserTokenVO refreshToken(String refreshToken) {
        LambdaQueryWrapper<SysUserTokenEntity> query = Wrappers.lambdaQuery();
        query.eq(SysUserTokenEntity::getRefreshToken, refreshToken);
        query.ge(SysUserTokenEntity::getRefreshTokenExpire, new Date());

        // 不存在，则表示refreshToken错误，或者已过期
        SysUserTokenEntity entity = baseMapper.selectOne(query);
        if (entity == null) {
            throw new ServerException(ErrorCode.REFRESH_TOKEN_INVALID);
        }

        // 删除缓存信息
        tokenStoreCache.deleteUser(entity.getAccessToken());

        // 生成新 accessToken
        String accessToken = TokenUtils.generator();
        entity.setAccessToken(accessToken);
        entity.setAccessTokenExpire(DateUtil.toLocalDateTime(DateUtil.offsetSecond(new Date(), securityProperties.getAccessTokenExpire())));

        // 更新
        baseMapper.updateById(entity);

        // 设置用户权限信息
        SysUserEntity user = sysUserDao.selectById(entity.getUserId());
        UserDetail userDetail = SysUserConvert.INSTANCE.convertDetail(user);
        sysUserDetailsService.getUserDetails(userDetail);

        // 保存用户信息到缓存
        tokenStoreCache.saveUser(accessToken, userDetail);

        return SysUserTokenConvert.INSTANCE.convert(entity);
    }

    @Override
    public void expireToken(Long userId) {
        SysUserTokenEntity entity = new SysUserTokenEntity();
        LocalDateTime now = LocalDateTime.now();
        entity.setAccessTokenExpire(now);
        entity.setRefreshTokenExpire(now);

        baseMapper.update(entity, new LambdaQueryWrapper<SysUserTokenEntity>().eq(SysUserTokenEntity::getUserId, userId));
    }

    @Async
    @Override
    public void updateCacheAuthByRoleId(Long roleId) {
        // 根据角色ID，查询用户 access_token 列表
        List<String> accessTokenList = baseMapper.getOnlineAccessTokenListByRoleId(roleId, LocalDateTime.now());

        accessTokenList.forEach(this::updateCacheAuth);
    }

    @Async
    @Override
    public void updateCacheAuthByUserId(Long userId) {
        // 根据用户ID，查询用户 access_token 列表
        List<String> accessTokenList = baseMapper.getOnlineAccessTokenListByUserId(userId, LocalDateTime.now());

        accessTokenList.forEach(this::updateCacheAuth);
    }

    /**
     * 根据accessToken，更新Cache里面的用户权限
     *
     * @param accessToken access_token
     */
    private void updateCacheAuth(String accessToken) {
        UserDetail user = tokenStoreCache.getUser(accessToken);
        // 用户不存在
        if (user == null) {
            return;
        }

        // 查询过期时间
        Long expire = tokenStoreCache.getExpire(accessToken);
        if (expire == null) {
            return;
        }

        // 设置用户权限信息
        sysUserDetailsService.getUserDetails(user);
        // 更新缓存
        tokenStoreCache.saveUser(accessToken, user, expire);

    }
}