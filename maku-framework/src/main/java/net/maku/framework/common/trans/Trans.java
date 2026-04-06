package net.maku.framework.common.trans;

import java.lang.annotation.*;

/**
 * 翻译注解，标记需要翻译的字段
 *
 * @author maku
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trans {
    /**
     * 翻译类型
     *
     * @see TransType
     */
    String type();

    /**
     * 字典类型key（字典翻译时使用）
     */
    String key() default "";

    /**
     * 翻译结果存放字段名
     */
    String ref() default "";

    /**
     * 目标实体类（SIMPLE翻译时使用）
     */
    Class<?> target() default Object.class;

    /**
     * 要翻译的字段名（SIMPLE翻译时使用）
     */
    String fields() default "";
}
