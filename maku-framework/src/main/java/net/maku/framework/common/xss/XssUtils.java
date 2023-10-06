package net.maku.framework.common.xss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

/**
 * XSS 过滤工具类
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public class XssUtils {
    /**
     * 不格式化
     */
    private final static Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    /**
     * XSS过滤
     *
     * @param content 需要过滤的内容
     * @return 返回过滤后的内容
     */
    public static String filter(String content) {
        return Jsoup.clean(content, "", Safelist.relaxed(), outputSettings);
    }

}