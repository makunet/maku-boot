package net.maku.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件配置
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(description = "邮件配置")
public class SysMailConfigVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "平台类型")
    private Integer platform;

    @Schema(description = "分组名称，发送邮件时，可指定分组")
    private String groupName;

    @Schema(description = "SMTP服务器")
    private String mailHost;

    @Schema(description = "SMTP端口")
    private Integer mailPort;

    @Schema(description = "发件人邮箱")
    private String mailFrom;

    @Schema(description = "发件人密码")
    private String mailPass;

    @Schema(description = "regionId")
    private String regionId;

    @Schema(description = "阿里云 endpoint")
    private String endpoint;

    @Schema(description = "AccessKey")
    private String accessKey;

    @Schema(description = "SecretKey")
    private String secretKey;

    @Schema(description = "状态  0：禁用   1：启用")
    private Integer status;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "删除标识")
    private Integer deleted;

    @Schema(description = "创建者")
    private Long creator;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "更新者")
    private Long updater;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;


}