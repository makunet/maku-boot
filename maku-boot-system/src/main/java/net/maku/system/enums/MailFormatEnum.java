package net.maku.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 邮件格式枚举
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Getter
@AllArgsConstructor
public enum MailFormatEnum {
    /**
     * 纯文本
     */
    TEXT,
    /**
     * HTML
     */
    HTML,
    /**
     * 模板
     */
    TEMPLATE;
}
