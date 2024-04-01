package net.maku.member.service;

import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.member.entity.MemberUserEntity;
import net.maku.member.query.MemberUserQuery;
import net.maku.member.vo.MemberUserVO;

import java.util.List;

/**
 * 会员管理
 *
 * @author 阿沐 babamu@126.com
 */
public interface MemberUserService extends BaseService<MemberUserEntity> {

    PageResult<MemberUserVO> page(MemberUserQuery query);

    void save(MemberUserVO vo);

    void update(MemberUserVO vo);

    void delete(List<Long> idList);
}