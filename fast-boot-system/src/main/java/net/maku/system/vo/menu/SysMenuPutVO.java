package net.maku.system.vo.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 菜单管理
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单更新")
public class SysMenuPutVO extends SysMenuPostVO {
	@Schema(description = "id", required = true)
	@NotNull(message = "id不能为空")
	private Long id;

}