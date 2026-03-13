package com.lx.user_center.common;

/**
 * 错误码
 * @author lx
 *
 */
public enum ErrorCode {

    SUCCESS(0,"success",""),
    PARAMS_ERROR(4000,"请求参数错误",""),
    NULL_ERROR(4001,"请求参数为空",""),
    NO_LOGIN(4002,"未登录",""),
    NO_AUTH(4003,"没有权限","");

    /**
     * 状态码
     */
    private final  int code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码信息描述
     */
    private  final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }
}
