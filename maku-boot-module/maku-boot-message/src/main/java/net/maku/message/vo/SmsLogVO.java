package net.maku.message.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 短信日志
*
* @author 阿沐 babamu@126.com
*/
@Data
@Schema(description = "短信日志")
public class SmsLogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "平台ID")
	private Long platformId;

	@Schema(description = "平台类型")
	private Integer platform;

	@Schema(description = "手机号")
	private String mobile;

	@Schema(description = "状态  0：失败   1：成功")
	private Integer status;

	@Schema(description = "参数")
	private String params;

	@Schema(description = "异常信息")
	private String error;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

}