package net.maku.system.dao;

import net.maku.framework.mybatis.dao.BaseDao;
import net.maku.system.entity.SysSmsConfigEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信配置
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysSmsConfigDao extends BaseDao<SysSmsConfigEntity> {

}