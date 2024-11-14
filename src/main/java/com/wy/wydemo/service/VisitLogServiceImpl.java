package com.wy.wydemo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.mapper.VisitLogMapper;
import com.wy.wydemo.model.entity.VisitLog;
import com.wy.wydemo.model.vo.query.LogQuery;
import com.wy.wydemo.model.vo.response.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @description:
 * @class: VisitLogServiceImpl
 * @author: yu_wei
 * @create: 2024/11/08 14:40
 */
@Service
public class VisitLogServiceImpl extends ServiceImpl<VisitLogMapper, VisitLog> implements VisitLogService{
    
    
    
    @Autowired
    private VisitLogMapper visitLogMapper;
    
    public void saveVisitLog(VisitLog visitLog) {
        // 保存访问日志
        visitLogMapper.insert(visitLog);
    }
    
    /**
     * 查看访问日志
     * @param logQuery
     * @return
     */
    @Override
    public PageResult<VisitLog> listVisitLog(LogQuery logQuery) {
        // 查询访问日志数量
        Long count = visitLogMapper.selectCount(new LambdaQueryWrapper<VisitLog>()
                .like(StringUtils.hasText(logQuery.getKeyword()), VisitLog::getPage, logQuery.getKeyword())
        );
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询访问日志列表
        List<VisitLog> visitLogVOList = visitLogMapper.selectVisitLogList(logQuery);
        return new PageResult<>(visitLogVOList, count);
    }
}
