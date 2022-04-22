package net.maku.system.vo.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 菜单管理
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(name = "菜单新增")
public class SysMenuPostVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(name = "上级ID")
	@NotNull(message = "上级ID不能为空")
	private Long pid;

	@Schema(name = "菜单名称")
	@NotBlank(message = "菜单名称不能为空")
	private String name;

	@Schema(name = "菜单URL")
	private String url;

	@Schema(name = "类型  0：菜单   1：按钮   2：接口")
	@Range(min = 0, max = 2, message = "类型不正确")
	private Integer type;

	@Schema(name = "打开方式   0：内部   1：外部")
	@Range(min = 0, max = 1, message = "打开方式不正确")
	private Integer openStyle;

	@Schema(name = "菜单图标")
	private String icon;

	@Schema(name = "授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)")
	private String authority;

	@Schema(name = "排序")
	@Min(value = 0, message = "排序值不能小于0")
	private Integer sort;
}