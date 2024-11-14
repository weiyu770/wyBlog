package com.wy.wydemo.model.enums;

/**
 * @description: 自定义状态码枚举，包含错误码和状态码
 * @class: StatusCodeEnum
 */
public enum StatusCodeEnum {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    
    /**
     * 请求参数错误
     */
    PARAMS_ERROR(40000, "请求参数错误"),

    /**
     * 参数错误（通用）
     */
    VALID_ERROR(400, "参数错误"),

    /**
     * 未登录
     */
    NOT_LOGIN_ERROR(40100, "未登录"),
    
    /**
     * 无权限
     */
    NO_AUTH_ERROR(40101, "无权限"),

    /**
     * 未登录（通用）
     */
    UNAUTHORIZED(402, "未登录"),
    
    /**
     * 禁止访问
     */
    FORBIDDEN_ERROR(40300, "禁止访问"),
    
    /**
     * 请求数据不存在
     */
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    
    /**
     * 系统内部异常
     */
    SYSTEM_ERROR(50000, "系统内部异常"),
    
    /**
     * 系统异常（通用）
     */
    GENERIC_SYSTEM_ERROR(-1, "系统异常"),
    
    /**
     * 操作失败
     */
    OPERATION_ERROR(50001, "操作失败"),

    /**
     * 操作失败（通用）
     */
    FAIL(500, "操作失败"),
    
    
    // 数据库未连接
    DATABASE_CONNECTION_ERROR(50002, "数据库未连接");
    
    public Integer getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 返回信息
     */
    private final String message;
    
    
    StatusCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
