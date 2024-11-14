package com.wy.wydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.mapper.ExceptionLogMapper;
import com.wy.wydemo.mapper.OperationLogMapper;
import com.wy.wydemo.model.entity.ExceptionLog;
import com.wy.wydemo.model.vo.query.LogQuery;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @description:
 * @class: ExceptionLogServiceImpl
 * @author: yu_wei
 * @create: 2024/11/08 12:32
 */
@Service
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements ExceptionLogService {
    
    
    @Autowired
    private OperationLogMapper operationLogMapper;
    
    /**
     * 查看异常日志
     *
     * @param logQuery
     * @return
     */
    @Autowired
    private ExceptionLogMapper exceptionLogMapper;
    
    @Override
    public PageResult<ExceptionLog> listExceptionLog(LogQuery logQuery) {
        // 查询异常日志数量
        Long count = exceptionLogMapper.selectCount(new LambdaQueryWrapper<ExceptionLog>()
                .like(StringUtils.hasText(logQuery.getOptModule()), ExceptionLog::getModule, logQuery.getOptModule())
                .or()
                .like(StringUtils.hasText(logQuery.getKeyword()), ExceptionLog::getDescription, logQuery.getKeyword())
        );
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询异常日志列表
        List<ExceptionLog> operationLogVOList = exceptionLogMapper.selectExceptionLogList(logQuery);
        return new PageResult<>(operationLogVOList, count);
    }
    
}
