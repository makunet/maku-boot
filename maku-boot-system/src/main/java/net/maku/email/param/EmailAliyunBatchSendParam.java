package net.maku.email.param;

import lombok.Data;

/**
 * 阿里云 批量发送邮件参数
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
public class EmailAliyunBatchSendParam {
    /**
     * 分组名称，非必填
     */
    private String groupName;
    /**
     * 发件人邮箱
     */
    private String from;
    /**
     * 收件人列表名称
     */
    private String receiversName;
    /**
     * 邮件模板名称
     */
    private String templateName;
    /**
     * 邮件标签
     */
    private String tagName;
}
