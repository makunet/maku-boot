package net.maku.system.convert;

import net.maku.framework.security.user.UserDetail;
import net.maku.system.entity.SysUserEntity;
import net.maku.system.vo.user.SysUserPostVO;
import net.maku.system.vo.user.SysUserPutVO;
import net.maku.system.vo.user.SysUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysUserConvert {
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserEntity convert(SysUserPostVO vo);

    SysUserEntity convert(SysUserPutVO vo);

    SysUserVO convert(SysUserEntity entity);

    SysUserVO convert(UserDetail userDetail);

    UserDetail convertDetail(SysUserEntity entity);

    List<SysUserVO> convertList(List<SysUserEntity> list);

}
