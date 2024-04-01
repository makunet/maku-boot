package net.maku.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 第三方登录配置
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@TableName("sys_third_login_config")
public class SysThirdLoginConfigEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 开放平台类型
     */
    private String openType;

    /**
     * ClientID
     */
    private String clientId;

    /**
     * ClientSecret
     */
    private String clientSecret;

    /**
     * RedirectUri
     */
    private String redirectUri;

    /**
     * AgentID
     */
    private String agentId;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 版本号
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    /**
     * 删除标记
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}