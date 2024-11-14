package com.wy.wydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.mapper.TaskLogMapper;
import com.wy.wydemo.model.entity.TaskLog;
import com.wy.wydemo.model.vo.query.TaskQuery;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.model.vo.response.TaskLogResp;
import com.wy.wydemo.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @class: TaskLogServiceImpl
 * @author: yu_wei
 * @create: 2024/11/08 14:50
 */
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog> implements TaskLogService {
    
    @Autowired
    private TaskLogMapper taskLogMapper;
    
    /**
     * 查看定日任务日志
     *
     * @param taskQuery
     * @return
     */
    @Override
    public PageResult<TaskLogResp> listTaskLog(TaskQuery taskQuery) {
        // 查询定时任务日志数量
        Long count = taskLogMapper.selectTaskLogCount(taskQuery);
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询定时任务日志列表
        List<TaskLogResp> taskLogRespList = taskLogMapper.selectTaskLogRespList(taskQuery);
        return new PageResult<>(taskLogRespList, count);
    }
    
    /**
     * 清空定时任务日志
     */
    @Override
    public void clearTaskLog() {
        taskLogMapper.delete(null);
    }
    
}
