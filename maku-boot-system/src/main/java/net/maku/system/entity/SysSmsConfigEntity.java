package net.maku.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.maku.framework.mybatis.entity.BaseEntity;

/**
 * 短信平台
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_sms_config")
public class SysSmsConfigEntity extends BaseEntity {
    /**
     * 平台类型  0：阿里云   1：腾讯云   2：七牛云    3：华为云
     */
    private Integer platform;

    /**
     * 分组名称，发送短信时，可指定分组
     */
    private String groupName;

    /**
     * 短信签名
     */
    private String signName;

    /**
     * 短信模板
     */
    private String templateId;

    /**
     * 短信应用的ID，如：腾讯云等
     */
    private String appId;

    /**
     * 腾讯云国际短信、华为云等需要
     */
    private String senderId;

    /**
     * 接入地址，如：华为云
     */
    private String url;

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