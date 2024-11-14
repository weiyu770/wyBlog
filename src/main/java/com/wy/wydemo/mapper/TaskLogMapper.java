package com.wy.wydemo.mapper;

import co.elastic.clients.elasticsearch.xpack.usage.Base;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.TaskLog;
import com.wy.wydemo.model.vo.query.TaskQuery;
import com.wy.wydemo.model.vo.response.TaskLogResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: TaskLogMapper
 * @author: yu_wei
 * @create: 2024/11/08 14:51
 */
@Mapper
public interface TaskLogMapper extends BaseMapper<TaskLog> {
    /**
     * 查询定时任务日志数量
     *
     * @param taskQuery 条件
     * @return 定时任务日志数量
     */
    Long selectTaskLogCount(@Param("param") TaskQuery taskQuery);
    
    /**
     * 查询定时任务日志列表
     *
     * @param taskQuery 条件
     * @return 定时任务日志列表
     */
    List<TaskLogResp> selectTaskLogRespList(@Param("param") TaskQuery taskQuery);
}
