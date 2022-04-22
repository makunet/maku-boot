package net.maku.system.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 角色管理
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "角色更新")
public class SysRolePutVO extends SysRolePostVO {
	@Schema(name = "id", required = true)
	@NotNull(message = "id不能为空")
	private Long id;

}