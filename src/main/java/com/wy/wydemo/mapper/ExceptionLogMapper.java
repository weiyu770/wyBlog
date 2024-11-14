package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.ExceptionLog;
import com.wy.wydemo.model.vo.query.LogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: ExceptionLogMapper
 * @author: yu_wei
 * @create: 2024/11/08 12:34
 */
@Mapper
public interface ExceptionLogMapper extends BaseMapper<ExceptionLog> {
    
    /**
     * 查询异常日志
     *
     * @param logQuery 异常日志查询条件
     * @return 异常日志列表
     */
    List<ExceptionLog> selectExceptionLogList(@Param("param") LogQuery logQuery);
}




