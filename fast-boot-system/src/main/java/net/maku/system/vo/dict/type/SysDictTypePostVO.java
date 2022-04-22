package net.maku.system.vo.dict.type;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 字典类型
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(name = "字典类型新增")
public class SysDictTypePostVO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(name = "字典类型", required = true)
	@NotBlank(message = "字典类型不能为空")
	private String dictType;

	@Schema(name = "字典名称", required = true)
	@NotBlank(message = "字典名称不能为空")
	private String dictName;

	@Schema(name = "备注")
	private String remark;

	@Schema(name = "排序", required = true)
	@Min(value = 0, message = "排序值不能小于0")
	private Integer sort;
}