package net.maku.system.vo.dict.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 字典数据
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "字典数据更新")
public class SysDictDataPutVO extends SysDictDataPostVO {
	@Schema(name = "id", required = true)
	@NotNull(message = "id不能为空")
	private Long id;

}