package net.maku.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 第三方登录配置
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "第三方登录配置")
public class SysThirdLoginConfigVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "开放平台类型")
    private String openType;

    @Schema(description = "ClientID")
    private String clientId;

    @Schema(description = "ClientSecret")
    private String clientSecret;

    @Schema(description = "RedirectUri")
    private String redirectUri;

    @Schema(description = "AgentID")
    private String agentId;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;


}