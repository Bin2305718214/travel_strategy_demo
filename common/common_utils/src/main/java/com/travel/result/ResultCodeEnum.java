package com.travel.result;

import lombok.Getter;

/**
 * @author Build_start
 * @create 2022-12-18 14:40
 */

@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    UPDATE_FAIL(202, "更新信息失败"),

    SAVE_ERROR(205,"保存失败"),
    DELETE_ERROR(305, "删除失败"),
    DATA_ERROR(500, "数据异常"),

    LOGIN_AUTH(303, "未登陆"),
    PERMISSION(403, "没有权限"),
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
