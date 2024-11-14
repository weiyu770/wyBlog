package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Task;
import com.wy.wydemo.model.vo.query.TaskQuery;
import com.wy.wydemo.model.vo.response.TaskBackResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: TaskMapper
 * @author: yu_wei
 * @create: 2024/11/11 09:39
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {
    /**
     * 查询定时任务数量
     *
     * @param taskQuery 任务查询条件
     * @return 数量
     */
    Long selectTaskCount(@Param("param") TaskQuery taskQuery);
    
    /**
     * 查询定时任务列表
     *
     * @param taskQuery 任务查询条件
     * @return 定时任务列表
     */
    List<TaskBackResp> selectTaskList(@Param("param") TaskQuery taskQuery);
}
