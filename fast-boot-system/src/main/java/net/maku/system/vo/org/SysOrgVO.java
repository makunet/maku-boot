package net.maku.system.vo.org;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.utils.DateUtils;
import net.maku.framework.common.utils.TreeNode;

import java.util.Date;

/**
 * 机构列表
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "机构")
public class SysOrgVO extends TreeNode<SysOrgVO> {
	@Schema(description = "id")
	private Long id;

	@Schema(description = "上级ID")
	private Long pid;

	@Schema(description = "机构名称")
	private String name;

	@Schema(description = "排序")
	private Integer sort;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "上级名称")
	private String parentName;

}