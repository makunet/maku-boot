package net.maku.system.convert;

import net.maku.system.entity.SysRoleEntity;
import net.maku.system.vo.role.SysRolePostVO;
import net.maku.system.vo.role.SysRolePutVO;
import net.maku.system.vo.role.SysRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysRoleConvert {
    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    SysRoleEntity convert(SysRolePostVO vo);

    SysRoleEntity convert(SysRolePutVO vo);

    SysRoleVO convert(SysRoleEntity entity);
    
    List<SysRoleVO> convertList(List<SysRoleEntity> list);

}
