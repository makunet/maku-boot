package net.maku.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.maku.framework.common.utils.DateUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 客户端管理
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(description = "客户端管理")
public class SysOauthClientVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id", required = true)
    private Long id;

    @Schema(description = "客户端ID", required = true)
    @NotBlank(message = "客户端ID不能为空")
    private String clientId;

    @Schema(description = "客户端密钥", required = true)
    @NotBlank(message = "客户端密钥不能为空")
    private String clientSecret;

    @Schema(description = "资源ids")
    private String resourceIds;

    @Schema(description = "授权范围", required = true)
    @NotBlank(message = "授权范围不能为空")
    private String scope;

    @Schema(description = "授权类型")
    private String[] authorizedGrantTypes;

    @Schema(description = "回调地址")
    private String webServerRedirectUri;

    @Schema(description = "权限标识")
    private String authorities;

    @Schema(description = "访问令牌有效期", required = true)
    @Min(value = 0, message = "访问令牌有效期不能小于0")
    private Integer accessTokenValidity;

    @Schema(description = "刷新令牌有效期", required = true)
    @Min(value = 0, message = "刷新令牌有效期不能小于0")
    private Integer refreshTokenValidity;

    @Schema(description = "附加信息")
    private String additionalInformation;

    @Schema(description = "自动授权", required = true)
    @NotBlank(message = "自动授权不能为空")
    private String autoapprove;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;
}
