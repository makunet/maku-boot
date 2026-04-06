package net.maku.framework.common.trans;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 翻译反射工具类
 *
 * @author maku
 */
public class TransReflectUtils {

    /**
     * 获取带有指定注解的字段列表
     *
     * @param clazz           类
     * @param annotationClass 注解类型
     * @return 字段列表
     */
    public static List<Field> getAnnotationField(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        List<Field> fields = new ArrayList<>();
        Class<?> currentClass = clazz;
        while (currentClass != null && currentClass != Object.class) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(annotationClass)) {
                    fields.add(field);
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }

    /**
     * 获取声明的字段（含父类）
     *
     * @param clazz     类
     * @param fieldName 字段名
     * @return 字段
     */
    public static Field getDeclaredField(Class<?> clazz, String fieldName) {
        Class<?> currentClass = clazz;
        while (currentClass != null && currentClass != Object.class) {
            try {
                return currentClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                currentClass = currentClass.getSuperclass();
            }
        }
        return null;
    }
}
