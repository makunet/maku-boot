package net.maku.message.entity;

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
@TableName("sms_platform")
public class SmsPlatformEntity extends BaseEntity {
    /**
     * 平台类型  0：阿里云   1：腾讯云   2：七牛云    3：华为云
     */
    private Integer platform;

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