package net.maku.email.util;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;
import net.maku.email.config.EmailConfig;
import net.maku.framework.common.exception.ServerException;

import java.io.File;

/**
 * 本地 邮件发送
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Slf4j
public class EmailLocalUtil {

    private final MailAccount mailAccount;

    public EmailLocalUtil(EmailConfig config) {
        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost(config.getMailHost());
        if (config.getMailPort() != null) {
            mailAccount.setPort(config.getMailPort());
            // 开启SSL加密
            if (config.getMailPort() == 465 || config.getMailPort() == 587) {
                mailAccount.setSslEnable(true);
            }
        }
        mailAccount.setFrom(config.getMailFrom());
        mailAccount.setPass(config.getMailPass());
        this.mailAccount = mailAccount;
    }

    /**
     * 发送邮件
     *
     * @param tos     收件人邮箱列表，多个收件人逗号隔开
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param files   附件列表
     * @return message-id
     */
    public String sendEmail(String tos, String subject, String content, boolean isHtml, File... files) {
        try {
            return MailUtil.send(mailAccount, tos, subject, content, isHtml, files);
        } catch (Exception e) {
            log.error("发送邮件失败", e);
            throw new ServerException(e.getMessage());
        }
    }
}
