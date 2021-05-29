package com.qzy.enums;

import lombok.Getter;


@Getter
public enum ResultEnum {

    SUCCESS(20000, "操作成功！"),
    ERROR(40000, "操作失败！"),
    DATA_NOT_FOUND(40001, "查询失败！"),
    PARAMS_NULL(40002, "参数不能为空！"),
    PARAMS_ERROR(40005, "参数不合法！"),

    NOT_LOGIN(40003, "当前账号未登录！"),
    LOGIN_PARAM_ERROR(40004, "用户名密码错误");
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
