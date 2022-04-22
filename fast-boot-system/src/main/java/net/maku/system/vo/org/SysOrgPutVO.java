package net.maku.system.vo.org;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 机构更新
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "机构更新")
public class SysOrgPutVO extends SysOrgPostVO {
	@Schema(name = "id", required = true)
	@NotNull(message = "ID不能为空")
	private Long id;

}