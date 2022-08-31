package net.maku.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 超级管理员枚举
 */
@Getter
@AllArgsConstructor
public enum SuperAdminEnum {
    YES(1),
    NO(0);

    private final Integer value;
}