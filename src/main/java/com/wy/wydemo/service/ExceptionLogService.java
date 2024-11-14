package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.ExceptionLog;
import com.wy.wydemo.model.vo.query.LogQuery;
import com.wy.wydemo.model.vo.response.PageResult;

/**
 * @description:
 * @class: ExceptionLogServiceImpl
 * @author: yu_wei
 * @create: 2024/11/08 12:31
 */
public interface ExceptionLogService extends IService<ExceptionLog> {
    /**
     * 查看异常日志
     * @param logQuery
     * @return
     */
    PageResult<ExceptionLog> listExceptionLog(LogQuery logQuery);
}
