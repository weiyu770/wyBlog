package com.wy.wydemo.exception;

import com.wy.wydemo.model.enums.StatusCodeEnum;

/**
 * @description:
 * @class: ThrowUtils
 * @author: yu_wei
 * @create: 2024/10/26 23:20
 */
public class ThrowUtils {
    
    /**
     * 条件成立则抛异常
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }
    
    /**
     * 条件成立则抛异常
     * @param condition
     * @param StatusCodeEnum
     */
    public static void throwIf(boolean condition, StatusCodeEnum StatusCodeEnum) {
        throwIf(condition, new BusinessException(StatusCodeEnum));
    }
    
    /**
     * 条件成立则抛异常
     * @param condition
     * @param StatusCodeEnum
     * @param message
     */
    public static void throwIf(boolean condition, StatusCodeEnum StatusCodeEnum, String message) {
        throwIf(condition, new BusinessException(StatusCodeEnum, message));
    }
}
