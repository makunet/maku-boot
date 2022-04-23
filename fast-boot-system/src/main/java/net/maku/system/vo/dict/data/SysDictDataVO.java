package net.maku.system.vo.dict.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.utils.DateUtils;

import java.util.Date;

/**
 * 字典数据
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "字典数据")
public class SysDictDataVO extends SysDictDataPostVO {
	@Schema(description = "id")
	private Long id;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;
}