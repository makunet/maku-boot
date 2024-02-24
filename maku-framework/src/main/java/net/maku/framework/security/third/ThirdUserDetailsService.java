package net.maku.framework.security.third;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 第三方登录，ThirdUserDetailsService
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public interface ThirdUserDetailsService {

    /**
     * 通过开放平台类型和唯一标识，加载用户信息
     *
     * @param openType 开放平台类型
     * @param openId   开放平台唯一标识
     * @return 用户信息
     * @throws UsernameNotFoundException 不存在异常
     */
    UserDetails loadUserByOpenTypeAndOpenId(String openType, String openId) throws UsernameNotFoundException;
}
