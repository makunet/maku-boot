package net.maku.message.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 短信平台
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "短信平台")
public class SmsPlatformVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "平台类型  0：阿里云   1：腾讯云")
    private Integer platform;

    @Schema(description = "短信签名")
    private String signName;

    @Schema(description = "短信模板")
    private String templateId;

    @Schema(description = "短信应用的ID，如：腾讯云等")
    private String appId;

    @Schema(description = "腾讯云国际短信、华为云等需要")
    private String senderId;

    @Schema(description = "接入地址，如：华为云")
    private String url;

    @Schema(description = "AccessKey")
    private String accessKey;

    @Schema(description = "SecretKey")
    private String secretKey;

    @Schema(description = "状态  0：禁用   1：启用")
    private Integer status;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;

}