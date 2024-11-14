package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.VisitLog;
import com.wy.wydemo.model.vo.query.LogQuery;
import com.wy.wydemo.model.vo.response.PageResult;

/**
 * @description:
 * @class: VisitLogService
 * @author: yu_wei
 * @create: 2024/11/08 14:39
 */
public interface VisitLogService extends IService<VisitLog> {
    
    /**
     * 查看访问日志
     * @param logQuery
     * @return
     */
    PageResult<VisitLog> listVisitLog(LogQuery logQuery);
}
