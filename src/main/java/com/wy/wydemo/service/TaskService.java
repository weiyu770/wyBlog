package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Task;
import com.wy.wydemo.model.vo.query.TaskQuery;
import com.wy.wydemo.model.vo.request.StatusReq;
import com.wy.wydemo.model.vo.request.TaskReq;
import com.wy.wydemo.model.vo.request.TaskRunReq;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.model.vo.response.TaskBackResp;

import java.util.List;

/**
 * @description:
 * @class: TaskService
 * @author: yu_wei
 * @create: 2024/11/11 09:37
 */
public interface TaskService  extends IService<Task> {
    
    /**
     * 查看定时任务列表
     * @param taskQuery
     * @return
     */
    PageResult<TaskBackResp> listTaskBackVO(TaskQuery taskQuery);
    
    
    /**
     * 添加定时任务
     * @param task
     */
    void addTask(TaskReq task);
    
    /**
     * 修改定时任务
     * @param task
     */
    void updateTask(TaskReq task);
    
    
    /**
     * 删除定时任务
     * @param taskIdList
     */
    void deleteTask(List<Integer> taskIdList);
    
    
    /**
     * 修改定时任务状态
     * @param taskStatus
     */
    void updateTaskStatus(StatusReq taskStatus);
    
    
    /**
     * 执行定时任务
     * @param taskRun
     */
    void runTask(TaskRunReq taskRun);
    
}
