package net.maku.system.dao;

import net.maku.framework.mybatis.dao.BaseDao;
import net.maku.system.entity.SysPostEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 岗位管理
*
* @author 阿沐 babamu@126.com
*/
@Mapper
public interface SysPostDao extends BaseDao<SysPostEntity> {
	
}