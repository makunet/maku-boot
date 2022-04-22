package net.maku.system.convert;

import net.maku.system.entity.SysDictDataEntity;
import net.maku.system.vo.dict.data.SysDictDataPostVO;
import net.maku.system.vo.dict.data.SysDictDataPutVO;
import net.maku.system.vo.dict.data.SysDictDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictDataConvert {
    SysDictDataConvert INSTANCE = Mappers.getMapper(SysDictDataConvert.class);

    SysDictDataEntity convert(SysDictDataPostVO vo);

    SysDictDataEntity convert(SysDictDataPutVO vo);

    SysDictDataVO convert(SysDictDataEntity entity);
    
    List<SysDictDataVO> convertList(List<SysDictDataEntity> list);

}
