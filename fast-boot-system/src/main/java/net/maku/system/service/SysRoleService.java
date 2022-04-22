package net.maku.system.service;

import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.BaseService;
import net.maku.system.entity.SysRoleEntity;
import net.maku.system.vo.role.SysRolePostVO;
import net.maku.system.vo.role.SysRolePutVO;
import net.maku.system.vo.role.SysRoleQuery;
import net.maku.system.vo.role.SysRoleVO;

import java.util.List;

/**
 * 角色
 * 
 * @author 阿沐 babamu@126.com
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

	PageResult<SysRoleVO> page(SysRoleQuery query);

	List<SysRoleVO> getList(SysRoleQuery query);

	void save(SysRolePostVO vo);

	void update(SysRolePutVO vo);

	void delete(List<Long> idList);
}
