package net.maku.system.convert;

import net.maku.system.entity.SysPostEntity;
import net.maku.system.vo.post.SysPostPostVO;
import net.maku.system.vo.post.SysPostPutVO;
import net.maku.system.vo.post.SysPostVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysPostConvert {
    SysPostConvert INSTANCE = Mappers.getMapper(SysPostConvert.class);

    SysPostEntity convert(SysPostPostVO vo);

    SysPostEntity convert(SysPostPutVO vo);

    SysPostVO convert(SysPostEntity entity);

    List<SysPostVO> convertList(List<SysPostEntity> list);

}
