package net.maku.email.param;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * 本地 发送邮件参数
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
public class EmailLocalSendParam {
    /**
     * 分组名称，非必填
     */
    private String groupName;
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
    /**
     * 附件列表
     */
    private List<File> files = CollectionUtil.newArrayList();
}
