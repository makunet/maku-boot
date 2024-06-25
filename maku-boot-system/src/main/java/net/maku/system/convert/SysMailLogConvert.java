package net.maku.system.convert;

import net.maku.system.entity.SysMailLogEntity;
import net.maku.system.vo.SysMailLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 邮件日志
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysMailLogConvert {
    SysMailLogConvert INSTANCE = Mappers.getMapper(SysMailLogConvert.class);

    SysMailLogEntity convert(SysMailLogVO vo);

    SysMailLogVO convert(SysMailLogEntity entity);

    List<SysMailLogVO> convertList(List<SysMailLogEntity> list);

}