package net.maku.email.util;

import com.aliyun.dm20151123.Client;
import com.aliyun.dm20151123.models.BatchSendMailRequest;
import com.aliyun.dm20151123.models.SingleSendMailRequest;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import net.maku.email.config.EmailConfig;
import net.maku.framework.common.exception.ServerException;

/**
 * 阿里云 邮件发送
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Slf4j
public class EmailAliyunUtil {

    private final Client client;

    public EmailAliyunUtil(EmailConfig emailConfig) throws Exception {
        Config config = new Config();
        config.setEndpoint(emailConfig.getEndpoint());
        config.setRegionId(emailConfig.getRegionId());
        config.setAccessKeyId(emailConfig.getAccessKey());
        config.setAccessKeySecret(emailConfig.getSecretKey());

        this.client = new Client(config);
    }

    /**
     * 发送邮件
     *
     * @param from      发件人邮箱
     * @param formAlias 发件人昵称
     * @param tos       收件人邮箱列表，多个收件人逗号隔开
     * @param subject   邮件主题
     * @param content   邮件内容
     * @param isHtml    是否HTML格式
     * @return env-id
     */
    public String sendEmail(String from, String formAlias, String tos, String subject, String content, boolean isHtml) {
        SingleSendMailRequest request = new SingleSendMailRequest();

        // 发件人邮箱
        request.setAccountName(from);

        // 发件人昵称
        request.setFromAlias(formAlias);

        // 地址类型：0-为随机账号，1-为发信地址
        request.setAddressType(1);

        // 使用管理台配置的回信地址
        request.setReplyToAddress(true);

        // 收件人邮箱
        request.setToAddress(tos);

        // 邮件主题
        request.setSubject(subject);

        // 是否HTML
        if (isHtml) {
            request.setHtmlBody(content);
        } else {
            request.setTextBody(content);
        }

        // 是否开启追踪功能，开启需要备案，0关闭，1开启
        request.setClickTrace("0");

        try {
            return client.singleSendMail(request).getBody().getEnvId();
        } catch (Exception e) {
            log.error("发送邮件失败", e);
            throw new ServerException(e.getMessage());
        }
    }

    /**
     * 批量发送邮件
     *
     * @param from          发件人邮箱
     * @param receiversName 收件人列表名称
     * @param templateName  邮件模板
     * @param tagName       邮件标签
     * @return env-id
     */
    public String batchSendEmail(String from, String receiversName, String templateName, String tagName) {
        BatchSendMailRequest request = new BatchSendMailRequest();

        // 发件人邮箱
        request.setAccountName(from);

        // 收件人列表名称
        request.setReceiversName(receiversName);

        // 邮件模板
        request.setTemplateName(templateName);

        // 地址类型：0-为随机账号，1-为发信地址
        request.setAddressType(1);

        // 标签
        request.setTagName(tagName);

        // 是否开启追踪功能，开启需要备案，0关闭，1开启
        request.setClickTrace("0");

        try {
            return client.batchSendMail(request).getBody().getEnvId();
        } catch (Exception e) {
            log.error("发送邮件失败", e);
            throw new ServerException(e.getMessage());
        }
    }

}
