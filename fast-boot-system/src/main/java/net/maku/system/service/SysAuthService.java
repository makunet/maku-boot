package net.maku.system.service;

import net.maku.system.vo.SysAccountLoginVO;
import net.maku.system.vo.SysMobileLoginVO;
import net.maku.system.vo.SysTokenVO;

/**
 * 权限认证服务
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysAuthService {

    /**
     * 账号密码登录
     *
     * @param login 登录信息
     */
    SysTokenVO loginByAccount(SysAccountLoginVO login);

    /**
     * 手机短信登录
     *
     * @param login 登录信息
     */
    SysTokenVO loginByMobile(SysMobileLoginVO login);

    /**
     * 发送手机验证码
     *
     * @param mobile 手机号
     */
    void sendCode(String mobile);

    /**
     * 退出登录
     *
     * @param accessToken accessToken
     */
    void logout(String accessToken);
}
