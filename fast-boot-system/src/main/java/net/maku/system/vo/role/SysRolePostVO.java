package net.maku.system.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 角色管理
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(name = "角色新增")
public class SysRolePostVO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(name = "角色名称")
	@NotBlank(message = "角色名称不能为空")
	private String name;

	@Schema(name = "备注")
	private String remark;

	@Schema(name = "数据范围", description = "0：全部数据  1：本部门及子部门数据  2：本部门数据  3：本人数据  4：自定义数据")
	private Integer dataScope;

	@Schema(name = "菜单ID列表")
	private List<Long> menuIdList;

	@Schema(name = "机构ID列表")
	private List<Long> orgIdList;

}