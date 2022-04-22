package net.maku.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.common.entity.BaseEntity;

/**
 * 客户端管理
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName(value = "sys_oauth_client", autoResultMap = true)
public class SysOauthClientEntity extends BaseEntity {
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 客户端密钥
     */
    private String clientSecret;
    /**
     * 资源ids
     */
    private String resourceIds;
    /**
     * 授权范围
     */
    private String scope;
    /**
     * 授权类型
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String[] authorizedGrantTypes;
    /**
     * 回调地址
     */
    private String webServerRedirectUri;
    /**
     * 权限标识
     */
    private String authorities;
    /**
     * 访问令牌有效期
     */
    private Integer accessTokenValidity;
    /**
     * 刷新令牌有效期
     */
    private Integer refreshTokenValidity;
    /**
     * 附加信息
     */
    private String additionalInformation;
    /**
     * 自动授权
     */
    private String autoapprove;
}
