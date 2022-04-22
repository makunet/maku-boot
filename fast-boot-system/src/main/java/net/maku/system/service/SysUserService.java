package net.maku.system.service;

import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.BaseService;
import net.maku.system.entity.SysUserEntity;
import net.maku.system.vo.user.SysUserPostVO;
import net.maku.system.vo.user.SysUserPutVO;
import net.maku.system.vo.user.SysUserQuery;
import net.maku.system.vo.user.SysUserVO;

import java.util.List;

/**
 * 用户管理
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysUserService extends BaseService<SysUserEntity> {

    PageResult<SysUserVO> page(SysUserQuery query);

    void save(SysUserPostVO vo);

    void update(SysUserPutVO vo);

    void delete(List<Long> idList);

    /**
     * 修改密码
     * @param id           用户ID
     * @param newPassword  新密码
     */
    void updatePassword(Long id, String newPassword);

}
