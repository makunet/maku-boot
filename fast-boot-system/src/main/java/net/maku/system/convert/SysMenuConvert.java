package net.maku.system.convert;

import net.maku.system.entity.SysMenuEntity;
import net.maku.system.vo.menu.SysMenuPostVO;
import net.maku.system.vo.menu.SysMenuPutVO;
import net.maku.system.vo.menu.SysMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysMenuConvert {
    SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

    SysMenuEntity convert(SysMenuPostVO vo);

    SysMenuEntity convert(SysMenuPutVO vo);

    SysMenuVO convert(SysMenuEntity entity);

    List<SysMenuVO> convertList(List<SysMenuEntity> list);

}
