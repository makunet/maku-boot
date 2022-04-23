package net.maku.system.vo.dict.type;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 字典类型
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "字典类型更新")
public class SysDictTypePutVO extends SysDictTypePostVO {
	@Schema(description = "id", required = true)
	@NotNull(message = "id不能为空")
	private Long id;

}