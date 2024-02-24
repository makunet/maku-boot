package net.maku.system.dao;

import net.maku.framework.mybatis.dao.BaseDao;
import net.maku.system.entity.SysThirdLoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 第三方登录
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Mapper
public interface SysThirdLoginDao extends BaseDao<SysThirdLoginEntity> {

}