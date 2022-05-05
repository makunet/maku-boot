package net.maku.system.convert;

import net.maku.system.entity.SysOauthClientEntity;
import net.maku.system.vo.SysOauthClientVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysOauthClientConvert {
    SysOauthClientConvert INSTANCE = Mappers.getMapper(SysOauthClientConvert.class);

    SysOauthClientVO convert(SysOauthClientEntity entity);

    SysOauthClientEntity convert(SysOauthClientVO vo);

    List<SysOauthClientVO> convertList(List<SysOauthClientEntity> list);

}
