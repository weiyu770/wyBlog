package com.wy.wydemo.utils;

import com.wy.wydemo.result.Result;
import com.wy.wydemo.model.enums.StatusCodeEnum;

/**
 * @description: 返回工具类
 * @class: ResultUtils
 */
public class ResultUtils {
    
    // 成功方法，接收泛型数据类型
    public static <T> Result<T> success(T data) {
        return Result.success(data);
    }
    
    // 成功方法，返回一个无数据的成功响应
    public static Result<Void> success() {
        return Result.success();
    }
    
    // 失败方法（根据 StatusCodeEnum）
    public static Result<?> error(StatusCodeEnum statusCodeEnum) {
        return Result.fail(statusCodeEnum.getCode(), statusCodeEnum.getMessage());
    }
    
    public static Result<?> error(int code, String message) {
        return Result.fail(code, message);
    }
    
    public static Result<?> error(StatusCodeEnum statusCodeEnum, String message) {
        return Result.fail(statusCodeEnum.getCode(), message);
    }
    
    /**
     * 失败 - 数据库未连接
     *
     * @return 返回数据库连接错误信息
     */
    public static Result<?> databaseConnectionError() {
        return Result.fail(StatusCodeEnum.DATABASE_CONNECTION_ERROR.getCode(), StatusCodeEnum.DATABASE_CONNECTION_ERROR.getMessage());
    }
}
