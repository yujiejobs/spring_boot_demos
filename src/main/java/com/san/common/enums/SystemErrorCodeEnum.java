package com.san.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.common.enums
 * @className SystemErrorCodeEnum
 * @description
 * @date 2019/05/19 21:23
 */
@Getter
@AllArgsConstructor
public enum SystemErrorCodeEnum {

    /**
     * SYSTEM_SUCCESS
     */
    SYSTEM_SUCCESS(200, "system.success"),
    /**
     * SYSTEM_ERROR
     */
    SYSTEM_ERROR(500, "system.error");

    private Integer code;
    private String desc;

    public static SystemErrorCodeEnum getEnumByCode(Integer code) {
        for (SystemErrorCodeEnum c : SystemErrorCodeEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }


    public static SystemErrorCodeEnum getEnumByDesc(String desc) {
        for (SystemErrorCodeEnum c : SystemErrorCodeEnum.values()) {
            if (c.getDesc().equals(desc)) {
                return c;
            }
        }
        return null;
    }
}
