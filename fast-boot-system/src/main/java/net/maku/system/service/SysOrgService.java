package net.maku.system.service;

import net.maku.framework.common.service.BaseService;
import net.maku.system.entity.SysOrgEntity;
import net.maku.system.vo.org.SysOrgPostVO;
import net.maku.system.vo.org.SysOrgPutVO;
import net.maku.system.vo.org.SysOrgVO;

import java.util.List;

/**
 * 机构管理
 * 
 * @author 阿沐 babamu@126.com
 */
public interface SysOrgService extends BaseService<SysOrgEntity> {

	List<SysOrgVO> getList();

	void save(SysOrgPostVO vo);

	void update(SysOrgPutVO vo);

	void delete(Long id);

	/**
	 * 根据机构ID，获取子机构ID列表(包含本机构ID)
	 * @param id   机构ID
	 */
	List<Long> getSubOrgIdList(Long id);
}