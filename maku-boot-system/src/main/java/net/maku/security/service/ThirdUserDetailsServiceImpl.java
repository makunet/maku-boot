package net.maku.security.service;

import lombok.AllArgsConstructor;
import net.maku.framework.security.third.ThirdUserDetailsService;
import net.maku.system.convert.SysUserConvert;
import net.maku.system.dao.SysUserDao;
import net.maku.system.entity.SysUserEntity;
import net.maku.system.service.SysThirdLoginService;
import net.maku.system.service.SysUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 第三方登录，ThirdUserDetailsService
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Service
@AllArgsConstructor
public class ThirdUserDetailsServiceImpl implements ThirdUserDetailsService {
    private final SysUserDetailsService sysUserDetailsService;
    private final SysThirdLoginService sysThirdLoginService;
    private final SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByOpenTypeAndOpenId(String openType, String openId) throws UsernameNotFoundException {
        Long userId = sysThirdLoginService.getUserIdByOpenTypeAndOpenId(openType, openId);
        SysUserEntity userEntity = sysUserDao.getById(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException("绑定的系统用户，不存在");
        }

        return sysUserDetailsService.getUserDetails(SysUserConvert.INSTANCE.convertDetail(userEntity));
    }
}
