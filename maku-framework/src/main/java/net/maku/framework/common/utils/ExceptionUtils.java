package net.maku.framework.common.utils;

import cn.hutool.core.io.IoUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception工具类
 *
 * @author 阿沐 babamu@126.com
 */
public class ExceptionUtils {

    /**
     * 获取异常信息
     * @param e  异常
     * @return    返回异常信息
     */
    public static String getExceptionMessage(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);

        // 关闭IO流
        IoUtil.close(pw);
        IoUtil.close(sw);

        return sw.toString();
    }
}