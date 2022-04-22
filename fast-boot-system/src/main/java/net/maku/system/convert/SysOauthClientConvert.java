package net.maku.system.convert;

import net.maku.system.entity.SysOauthClientEntity;
import net.maku.system.vo.oauth.SysOauthClientPostVO;
import net.maku.system.vo.oauth.SysOauthClientPutVO;
import net.maku.system.vo.oauth.SysOauthClientVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysOauthClientConvert {
    SysOauthClientConvert INSTANCE = Mappers.getMapper(SysOauthClientConvert.class);

    SysOauthClientEntity convert(SysOauthClientPostVO vo);

    SysOauthClientEntity convert(SysOauthClientPutVO vo);

    SysOauthClientVO convert(SysOauthClientEntity entity);

    List<SysOauthClientVO> convertList(List<SysOauthClientEntity> list);

}
