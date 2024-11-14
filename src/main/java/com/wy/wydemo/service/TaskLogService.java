package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.TaskLog;
import com.wy.wydemo.model.vo.query.TaskQuery;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.model.vo.response.TaskLogResp;

/**
 * @description:
 * @class: TaskLogService
 * @author: yu_wei
 * @create: 2024/11/08 14:49
 */
public interface TaskLogService extends IService<TaskLog> {
    
    /**
     * 查看定时任务日志
     * @param taskQuery
     * @return
     */
    PageResult<TaskLogResp> listTaskLog(TaskQuery taskQuery);
    
    /**
     * 清空定时任务日志
     */
    void clearTaskLog();
    
}
