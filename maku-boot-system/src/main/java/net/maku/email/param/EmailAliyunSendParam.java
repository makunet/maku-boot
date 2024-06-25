package net.maku.email.param;

import lombok.Data;

/**
 * 阿里云 发送邮件参数
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
public class EmailAliyunSendParam {
    /**
     * 分组名称，非必填
     */
    private String groupName;
    /**
     * 发件人邮箱
     */
    private String from;
    /**
     * 发件人昵称
     */
    private String formAlias;
    /**
     * 收件人邮箱列表，逗号拼接
     */
    private String tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 是否为html格式
     */
    private boolean html;
}
