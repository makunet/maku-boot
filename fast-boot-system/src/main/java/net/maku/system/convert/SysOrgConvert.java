package net.maku.system.convert;

import net.maku.system.entity.SysOrgEntity;
import net.maku.system.vo.org.SysOrgPostVO;
import net.maku.system.vo.org.SysOrgPutVO;
import net.maku.system.vo.org.SysOrgVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysOrgConvert {
    SysOrgConvert INSTANCE = Mappers.getMapper(SysOrgConvert.class);

    SysOrgEntity convert(SysOrgPostVO vo);

    SysOrgEntity convert(SysOrgPutVO vo);

    SysOrgVO convert(SysOrgEntity entity);

    List<SysOrgVO> convertList(List<SysOrgEntity> list);

}
