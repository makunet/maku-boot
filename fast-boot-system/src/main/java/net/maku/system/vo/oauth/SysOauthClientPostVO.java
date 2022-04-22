package net.maku.system.vo.oauth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 客户端管理 新增
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@Schema(name = "客户端管理新增")
public class SysOauthClientPostVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(name = "客户端ID", required = true)
    @NotBlank(message = "客户端ID不能为空")
    private String clientId;

    @Schema(name = "客户端密钥", required = true)
    @NotBlank(message = "客户端密钥不能为空")
    private String clientSecret;

    @Schema(name = "资源ids")
    private String resourceIds;

    @Schema(name = "授权范围", required = true)
    @NotBlank(message = "授权范围不能为空")
    private String scope;

    @Schema(name = "授权类型")
    private String[] authorizedGrantTypes;

    @Schema(name = "回调地址")
    private String webServerRedirectUri;

    @Schema(name = "权限标识")
    private String authorities;

    @Schema(name = "访问令牌有效期", required = true)
    @Min(value = 0, message = "访问令牌有效期不能小于0")
    private Integer accessTokenValidity;

    @Schema(name = "刷新令牌有效期", required = true)
    @Min(value = 0, message = "刷新令牌有效期不能小于0")
    private Integer refreshTokenValidity;

    @Schema(name = "附加信息")
    private String additionalInformation;

    @Schema(name = "自动授权", required = true)
    @NotBlank(message = "自动授权不能为空")
    private String autoapprove;
}
