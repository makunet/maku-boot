package net.maku.system.service;

import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.BaseService;
import net.maku.system.entity.SysRoleEntity;
import net.maku.system.query.SysRoleQuery;
import net.maku.system.vo.SysRoleDataScopeVO;
import net.maku.system.vo.SysRoleVO;

import java.util.List;

/**
 * 角色
 * 
 * @author 阿沐 babamu@126.com
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

	PageResult<SysRoleVO> page(SysRoleQuery query);

	List<SysRoleVO> getList(SysRoleQuery query);

	void save(SysRoleVO vo);

	void update(SysRoleVO vo);

	void dataScope(SysRoleDataScopeVO vo);

	void delete(List<Long> idList);
}
