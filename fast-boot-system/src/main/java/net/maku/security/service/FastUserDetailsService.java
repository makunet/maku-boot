package net.maku.security.service;

import lombok.AllArgsConstructor;
import net.maku.framework.common.exception.ErrorCode;
import net.maku.framework.common.exception.FastException;
import net.maku.framework.security.user.UserDetail;
import net.maku.system.convert.SysUserConvert;
import net.maku.system.dao.SysRoleDao;
import net.maku.system.dao.SysRoleDataScopeDao;
import net.maku.system.dao.SysUserDao;
import net.maku.system.entity.SysUserEntity;
import net.maku.system.enums.DataScopeEnum;
import net.maku.system.enums.UserStatusEnum;
import net.maku.system.service.SysMenuService;
import net.maku.system.service.SysOrgService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * UserDetailsService
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class FastUserDetailsService implements UserDetailsService {
    private final SysMenuService sysMenuService;
    private final SysOrgService sysOrgService;
    private final SysUserDao sysUserDao;
    private final SysRoleDao sysRoleDao;
    private final SysRoleDataScopeDao sysRoleDataScopeDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity userEntity = sysUserDao.getByUsername(username);
        if(userEntity == null) {
            throw new FastException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        // 转换成UserDetail对象
        UserDetail userDetail = SysUserConvert.INSTANCE.convertDetail(userEntity);

        // 账号不可用
        if(userEntity.getStatus() == UserStatusEnum.DISABLE.getValue()){
            userDetail.setEnabled(false);
        }

        // 数据权限范围
        List<Long> dataScopeList = getDataScope(userDetail);
        userDetail.setDataScopeList(dataScopeList);

        // 用户权限列表
        Set<GrantedAuthority> authorities = getUserAuthority(userDetail);
        userDetail.setAuthorities(authorities);

        return userDetail;
    }

    private List<Long> getDataScope(UserDetail userDetail){
        Integer dataScope = sysRoleDao.getDataScopeByUserId(userDetail.getId());
        if (dataScope == null){
            return new ArrayList<>();
        }

        if (dataScope.equals(DataScopeEnum.ALL.getValue())) {
            // 全部数据权限，则返回null
            return null;
        } else if (dataScope.equals(DataScopeEnum.DEPT_AND_CHILD.getValue())) {
            // 本部门及子部门数据
            List<Long> dataScopeList = sysOrgService.getSubOrgIdList(userDetail.getOrgId());
            // 自定义数据权限范围
            dataScopeList.addAll(sysRoleDataScopeDao.getDataScopeList(userDetail.getId()));

            return dataScopeList;
        } else if (dataScope.equals(DataScopeEnum.DEPT_ONLY.getValue())) {
            // 本部门数据
            List<Long> dataScopeList = new ArrayList<>();
            dataScopeList.add(userDetail.getOrgId());
            // 自定义数据权限范围
            dataScopeList.addAll(sysRoleDataScopeDao.getDataScopeList(userDetail.getId()));

            return dataScopeList;
        }

        return new ArrayList<>();
    }

    private Set<GrantedAuthority> getUserAuthority(UserDetail user) {
        // 获取用户权限标识
        Set<String> permsSet = sysMenuService.getUserAuthority(user);

        // 封装权限标识
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(permsSet.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));

        return authorities;
    }
}
