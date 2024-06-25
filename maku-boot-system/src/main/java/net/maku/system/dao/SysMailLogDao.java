package net.maku.system.dao;

import net.maku.framework.mybatis.dao.BaseDao;
import net.maku.system.entity.SysMailLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 邮件日志
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysMailLogDao extends BaseDao<SysMailLogEntity> {

}