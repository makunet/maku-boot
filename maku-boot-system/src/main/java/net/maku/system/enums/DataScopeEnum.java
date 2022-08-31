package net.maku.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围枚举
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {
    /**
     * 全部数据
     */
    ALL(0),
    /**
     * 本部门及子部门数据
     */
    DEPT_AND_CHILD(1),
    /**
     * 本部门数据
     */
    DEPT_ONLY(2),
    /**
     * 本人数据
     */
    SELF(3),
    /**
     * 自定义数据
     */
    CUSTOM(4);

    private final Integer value;

}