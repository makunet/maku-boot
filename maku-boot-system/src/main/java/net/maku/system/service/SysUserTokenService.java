package net.maku.system.service;

import net.maku.framework.mybatis.service.BaseService;
import net.maku.system.entity.SysUserTokenEntity;
import net.maku.system.vo.SysUserTokenVO;


/**
 * 用户Token
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysUserTokenService extends BaseService<SysUserTokenEntity> {

    /**
     * 根据用户ID，生成用户Token
     *
     * @param userId 用户ID
     * @return 用户Token
     */
    SysUserTokenVO createToken(Long userId);

    /**
     * 根据refreshToken，生成新Token
     *
     * @param refreshToken refreshToken
     * @return 用户Token
     */
    SysUserTokenVO refreshToken(String refreshToken);

    /**
     * Token过期
     *
     * @param userId 用户ID
     */
    void expireToken(Long userId);

    /**
     * 根据角色ID，更新用户缓存权限
     *
     * @param roleId 角色ID
     */
    void updateCacheAuthByRoleId(Long roleId);

    /**
     * 根据用户ID，更新用户缓存权限
     *
     * @param userId 用户ID
     */
    void updateCacheAuthByUserId(Long userId);
}