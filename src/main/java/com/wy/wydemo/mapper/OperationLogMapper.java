package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.OperationLog;
import com.wy.wydemo.model.vo.query.LogQuery;
import com.wy.wydemo.model.vo.response.OperationLogResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: OperationLogMapper
 * @author: yu_wei
 * @create: 2024/11/08 12:21
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {
    /**
     * 查询操作日志
     *
     * @param logQuery 条件
     * @return 操作日志列表
     */
    List<OperationLogResp> selectOperationLogVOList(@Param("param") LogQuery logQuery);
}
