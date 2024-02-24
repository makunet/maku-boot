package net.maku.system.convert;

import net.maku.system.entity.SysThirdLoginConfigEntity;
import net.maku.system.vo.SysThirdLoginConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 第三方登录配置
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Mapper
public interface SysThirdLoginConfigConvert {
    SysThirdLoginConfigConvert INSTANCE = Mappers.getMapper(SysThirdLoginConfigConvert.class);

    SysThirdLoginConfigEntity convert(SysThirdLoginConfigVO vo);

    SysThirdLoginConfigVO convert(SysThirdLoginConfigEntity entity);

    List<SysThirdLoginConfigVO> convertList(List<SysThirdLoginConfigEntity> list);

}