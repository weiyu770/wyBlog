package com.wy.wydemo.exception;

import lombok.Getter;


import static com.wy.wydemo.model.enums.StatusCodeEnum.FAIL;

/**
 * @description: 业务异常
 * @class: ServiceException
 * @author: yu_wei
 * @create: 2024/11/04 15:50
 */
@Getter
public final class ServiceException extends RuntimeException {
    
    /**
     * 返回失败状态码
     */
    private final Integer code = FAIL.getCode();
    
    /**
     * 返回信息
     */
    private final String message;
    
    public ServiceException(String message) {
        this.message = message;
    }
    
}