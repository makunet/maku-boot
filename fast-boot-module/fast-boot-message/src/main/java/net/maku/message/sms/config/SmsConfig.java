package net.maku.message.sms.config;

import lombok.Data;

/**
 * 短信配置项
 *
 * @author 阿沐 babamu@126.com
 */
@Data
public class SmsConfig {
    /**
     * 平台ID
     */
    private Long id;

    /**
     * 平台类型
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

}
