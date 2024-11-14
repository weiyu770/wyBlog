package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.OperationLog;
import com.wy.wydemo.model.vo.query.LogQuery;
import com.wy.wydemo.model.vo.response.OperationLogResp;
import com.wy.wydemo.model.vo.response.PageResult;

/**
 * @description:
 * @class: OperationLogService
 * @author: yu_wei
 * @create: 2024/11/08 12:18
 */

public interface OperationLogService extends IService<OperationLog> {
    
    /**
     * 查看操作日志
     * @param logQuery
     * @return
     */
    PageResult<OperationLogResp> listOperationLogVO(LogQuery logQuery);
}
