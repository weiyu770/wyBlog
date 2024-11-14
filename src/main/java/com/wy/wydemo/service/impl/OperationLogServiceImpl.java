package com.wy.wydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.mapper.OperationLogMapper;
import com.wy.wydemo.model.entity.OperationLog;
import com.wy.wydemo.model.vo.query.LogQuery;
import com.wy.wydemo.model.vo.response.OperationLogResp;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @description:
 * @class: OperationLogService
 * @author: yu_wei
 * @create: 2024/11/08 12:19
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
    
    @Autowired
    private OperationLogMapper operationLogMapper;
    
    @Override
    public PageResult<OperationLogResp> listOperationLogVO(LogQuery logQuery) {
        // 查询操作日志数量
        Long count = operationLogMapper.selectCount(new LambdaQueryWrapper<OperationLog>()
                .like(StringUtils.hasText(logQuery.getOptModule()), OperationLog::getModule, logQuery.getOptModule())
                .or()
                .like(StringUtils.hasText(logQuery.getKeyword()), OperationLog::getDescription, logQuery.getKeyword())
        );
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询操作日志列表
        List<OperationLogResp> operationLogRespList = operationLogMapper.selectOperationLogVOList(logQuery);
        return new PageResult<>(operationLogRespList, count);
    }
}
