package net.maku.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.mybatis.entity.BaseEntity;

/**
 * 邮件平台
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_mail_config")
public class SysMailConfigEntity extends BaseEntity {
    /**
     * 平台类型  -1：本地   0：阿里云
     */
    private Integer platform;

    /**
     * 分组名称，发送邮件时，可指定分组
     */
    private String groupName;

    /**
     * SMTP服务器
     */
    private String mailHost;

    /**
     * SMTP端口
     */
    private Integer mailPort;

    /**
     * 发件人邮箱
     */
    private String mailFrom;

    /**
     * 发件人密码
     */
    private String mailPass;

    /**
     * regionId
     */
    private String regionId;

    /**
     * 阿里云 endpoint
     */
    private String endpoint;

    /**
     * AccessKey
     */
    private String accessKey;

    /**
     * SecretKey
     */
    private String secretKey;

    /**
     * 状态  0：禁用   1：启用
     */
    private Integer status;

}