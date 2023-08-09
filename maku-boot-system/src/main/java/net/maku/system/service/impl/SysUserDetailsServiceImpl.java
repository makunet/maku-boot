package net.maku.system.service.impl;

import lombok.AllArgsConstructor;
import net.maku.framework.security.user.UserDetail;
import net.maku.system.dao.SysRoleDao;
import net.maku.system.dao.SysRoleDataScopeDao;
import net.maku.system.enums.DataScopeEnum;
import net.maku.system.enums.UserStatusEnum;
import net.maku.system.service.SysMenuService;
import net.maku.system.service.SysOrgService;
import net.maku.system.service.SysUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户 UserDetails 信息
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Service
@AllArgsConstructor
public class SysUserDetailsServiceImpl implements SysUserDetailsService {
    private final SysMenuService sysMenuService;
    private final SysOrgService sysOrgService;
    private final SysRoleDao sysRoleDao;
    private final SysRoleDataScopeDao sysRoleDataScopeDao;

    @Override
    public UserDetails getUserDetails(UserDetail userDetail) {
        // 账号不可用
        if (userDetail.getStatus() == UserStatusEnum.DISABLE.getValue()) {
            userDetail.setEnabled(false);
        }

        // 数据权限范围
        List<Long> dataScopeList = getDataScope(userDetail);
        userDetail.setDataScopeList(dataScopeList);

        // 用户权限列表
        Set<String> authoritySet = sysMenuService.getUserAuthority(userDetail);

        // 用户角色编码列表
        List<String> roleCodeList = sysRoleDao.geRoleCodeByUserId(userDetail.getId());
        roleCodeList.forEach(roleCode -> authoritySet.add("ROLE_" + roleCode));

        userDetail.setAuthoritySet(authoritySet);

        return userDetail;
    }

    private List<Long> getDataScope(UserDetail userDetail) {
        Integer dataScope = sysRoleDao.getDataScopeByUserId(userDetail.getId());
        if (dataScope == null) {
            return new ArrayList<>();
        }

        if (dataScope.equals(DataScopeEnum.ALL.getValue())) {
            // 全部数据权限，则返回null
            return null;
        } else if (dataScope.equals(DataScopeEnum.ORG_AND_CHILD.getValue())) {
            // 本机构及子机构数据
            List<Long> dataScopeList = sysOrgService.getSubOrgIdList(userDetail.getOrgId());
            // 自定义数据权限范围
            dataScopeList.addAll(sysRoleDataScopeDao.getDataScopeList(userDetail.getId()));

            return dataScopeList;
        } else if (dataScope.equals(DataScopeEnum.ORG_ONLY.getValue())) {
            // 本机构数据
            List<Long> dataScopeList = new ArrayList<>();
            dataScopeList.add(userDetail.getOrgId());
            // 自定义数据权限范围
            dataScopeList.addAll(sysRoleDataScopeDao.getDataScopeList(userDetail.getId()));

            return dataScopeList;
        } else if (dataScope.equals(DataScopeEnum.CUSTOM.getValue())) {
            // 自定义数据权限范围
            return sysRoleDataScopeDao.getDataScopeList(userDetail.getId());
        }

        return new ArrayList<>();
    }
}
