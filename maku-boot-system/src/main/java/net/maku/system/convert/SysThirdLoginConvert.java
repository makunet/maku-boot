package net.maku.system.convert;

import net.maku.system.entity.SysThirdLoginEntity;
import net.maku.system.vo.SysThirdLoginVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 第三方登录
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Mapper
public interface SysThirdLoginConvert {
    SysThirdLoginConvert INSTANCE = Mappers.getMapper(SysThirdLoginConvert.class);

    SysThirdLoginEntity convert(SysThirdLoginVO vo);

    SysThirdLoginVO convert(SysThirdLoginEntity entity);

    List<SysThirdLoginVO> convertList(List<SysThirdLoginEntity> list);

}