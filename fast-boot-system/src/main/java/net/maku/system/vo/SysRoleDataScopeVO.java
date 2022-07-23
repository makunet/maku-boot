package net.maku.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色数据权限
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(description = "角色数据权限")
public class SysRoleDataScopeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	@NotNull(message = "角色ID不能为空")
	private Long id;

	@Schema(description = "数据范围  0：全部数据  1：本部门及子部门数据  2：本部门数据  3：本人数据  4：自定义数据")
	@NotNull(message = "数据范围不能为空")
	private Integer dataScope;

	@Schema(description = "机构ID列表")
	private List<Long> orgIdList;

}