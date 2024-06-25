package net.maku.system.convert;

import net.maku.system.entity.SysSmsLogEntity;
import net.maku.system.vo.SysSmsLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信日志
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysSmsLogConvert {
    SysSmsLogConvert INSTANCE = Mappers.getMapper(SysSmsLogConvert.class);

    SysSmsLogVO convert(SysSmsLogEntity entity);

    List<SysSmsLogVO> convertList(List<SysSmsLogEntity> list);

}