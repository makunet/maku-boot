package net.maku.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.maku.framework.mybatis.dao.BaseDao;
import net.maku.system.entity.SysParamsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数管理
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Mapper
public interface SysParamsDao extends BaseDao<SysParamsEntity> {

    default boolean isExist(String paramKey) {
        return this.exists(new QueryWrapper<SysParamsEntity>().eq("param_key" , paramKey));
    }
}