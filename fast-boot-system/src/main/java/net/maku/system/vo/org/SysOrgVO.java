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
@Schema(name = "机构")
public class SysOrgVO extends TreeNode<SysOrgVO> {
	@Schema(name = "id")
	private Long id;

	@Schema(name = "上级ID")
	private Long pid;

	@Schema(name = "机构名称")
	private String name;

	@Schema(name = "排序")
	private Integer sort;

	@Schema(name = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(name = "上级名称")
	private String parentName;

}