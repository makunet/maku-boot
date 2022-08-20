package net.maku.message.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
* 短信发送
*
* @author 阿沐 babamu@126.com
*/
@Data
@Schema(description = "短信发送")
public class SmsSendVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "手机号")
	private String mobile;

	@Schema(description = "参数Key")
	private String paramKey;

	@Schema(description = "参数Value")
	private String paramValue;

}