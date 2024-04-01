package net.maku.member.dao;

import net.maku.framework.mybatis.dao.BaseDao;
import net.maku.member.entity.MemberUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员管理
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface MemberUserDao extends BaseDao<MemberUserEntity> {

}