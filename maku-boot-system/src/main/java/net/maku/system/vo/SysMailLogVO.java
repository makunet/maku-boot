package net.maku.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 邮件日志
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(description = "邮件日志")
public class SysMailLogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "平台ID")
    private Long platformId;

    @Schema(description = "平台类型")
    private Integer platform;

    @Schema(description = "发件人邮箱")
    private String mailFrom;

    @Schema(description = "接受人邮箱")
    private String mailTos;

    @Schema(description = "邮件主题")
    private String subject;

    @Schema(description = "邮件内容")
    private String content;

    @Schema(description = "状态  0：失败   1：成功")
    private Integer status;

    @Schema(description = "异常信息")
    private String error;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;

}