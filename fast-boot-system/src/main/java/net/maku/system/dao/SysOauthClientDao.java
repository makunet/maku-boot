package net.maku.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.maku.framework.common.dao.BaseDao;
import net.maku.system.entity.SysOauthClientEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户端管理
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysOauthClientDao extends BaseDao<SysOauthClientEntity> {

    default SysOauthClientEntity getByClientId(String clientId){
        return this.selectOne(new LambdaQueryWrapper<SysOauthClientEntity>().eq(SysOauthClientEntity::getClientId, clientId));
    }
}
