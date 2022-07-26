package net.maku.framework.common.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import net.maku.framework.common.exception.FastException;

/**
 * 校验工具类
 *
 * @author 阿沐 babamu@126.com
 */
public class AssertUtils {

    public static void isBlank(String str, String variable) {
        if (StrUtil.isBlank(str)) {
            throw new FastException(variable + "不能为空");
        }
    }

    public static void isNull(Object object, String variable) {
        if (object == null) {
            throw new FastException(variable + "不能为空");
        }
    }

    public static void isArrayEmpty(Object[] array, String variable) {
        if(ArrayUtil.isEmpty(array)){
            throw new FastException(variable + "不能为空");
        }
    }

}