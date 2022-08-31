package net.maku.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色管理
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(description = "角色")
public class SysRoleVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "角色名称")
	@NotBlank(message = "角色名称不能为空")
	private String name;

	@Schema(description = "备注")
	private String remark;

	@Schema(description = "数据范围  0：全部数据  1：本部门及子部门数据  2：本部门数据  3：本人数据  4：自定义数据")
	private Integer dataScope;

	@Schema(description = "菜单ID列表")
	private List<Long> menuIdList;

	@Schema(description = "机构ID列表")
	private List<Long> orgIdList;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

}