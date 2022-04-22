package net.maku.system.convert;

import net.maku.system.entity.SysDictTypeEntity;
import net.maku.system.vo.dict.type.SysDictTypePostVO;
import net.maku.system.vo.dict.type.SysDictTypePutVO;
import net.maku.system.vo.dict.type.SysDictTypeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictTypeConvert {
    SysDictTypeConvert INSTANCE = Mappers.getMapper(SysDictTypeConvert.class);

    SysDictTypeEntity convert(SysDictTypePostVO vo);

    SysDictTypeEntity convert(SysDictTypePutVO vo);

    SysDictTypeVO convert(SysDictTypeEntity entity);
    
    List<SysDictTypeVO> convertList(List<SysDictTypeEntity> list);

}
