package net.maku.system.vo.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.utils.DateUtils;

import java.util.Date;

/**
 * 角色管理
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色")
public class SysRoleVO extends SysRolePostVO {
	@Schema(description = "id")
	private Long id;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

}