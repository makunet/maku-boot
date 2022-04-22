package net.maku.system.vo.org;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 机构
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(name = "机构新增")
public class SysOrgPostVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(name = "上级ID", required = true)
	@NotNull(message = "上级ID不能为空")
	private Long pid;

	@Schema(name = "机构名称", required = true)
	@NotBlank(message = "机构名称不能为空")
	private String name;

	@Schema(name = "排序", required = true)
	@Min(value = 0, message = "排序值不能小于0")
	private Integer sort;

}