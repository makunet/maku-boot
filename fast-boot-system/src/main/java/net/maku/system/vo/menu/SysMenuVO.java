package net.maku.system.vo.menu;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.utils.DateUtils;
import net.maku.framework.common.utils.TreeNode;

import java.util.Date;

/**
 * 菜单管理
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "菜单")
public class SysMenuVO extends TreeNode<SysMenuVO> {
	@Schema(name = "id")
	private Long id;

	@Schema(name = "上级ID")
	private Long pid;

	@Schema(name = "菜单名称")
	private String name;

	@Schema(name = "菜单URL")
	private String url;

	@Schema(name = "类型  0：菜单   1：按钮   2：接口")
	private Integer type;

	@Schema(name = "打开方式   0：内部   1：外部")
	private Integer openStyle;

	@Schema(name = "菜单图标")
	private String icon;

	@Schema(name = "授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)")
	private String authority;

	@Schema(name = "排序")
	private Integer sort;

	@Schema(name = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(name = "上级菜单名称")
	private String parentName;
}