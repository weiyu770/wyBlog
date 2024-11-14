package com.wy.wydemo.exception;

import com.wy.wydemo.model.enums.StatusCodeEnum;

/**
 * @description: 自定义异常类
 * @class: UserRegisterRequest
 * @author: yu_wei
 * @create: 2024/10/26 17:35
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(StatusCodeEnum StatusCodeEnum) {
        super(StatusCodeEnum.getMessage());
        this.code = StatusCodeEnum.getCode();
    }

    public BusinessException(StatusCodeEnum StatusCodeEnum, String message) {
        super(message);
        this.code = StatusCodeEnum.getCode();
    }

    public int getCode() {
        return code;
    }
}
