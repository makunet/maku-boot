package net.maku.member.convert;

import net.maku.member.entity.MemberUserEntity;
import net.maku.member.vo.MemberUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 会员管理
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface MemberUserConvert {
    MemberUserConvert INSTANCE = Mappers.getMapper(MemberUserConvert.class);

    MemberUserEntity convert(MemberUserVO vo);

    MemberUserVO convert(MemberUserEntity entity);

    List<MemberUserVO> convertList(List<MemberUserEntity> list);

}