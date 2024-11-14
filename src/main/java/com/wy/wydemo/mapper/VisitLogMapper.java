package com.wy.wydemo.mapper;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.VisitLog;
import com.wy.wydemo.model.vo.query.LogQuery;
import com.wy.wydemo.model.vo.response.UserViewResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: VisitLogMapper
 * @author: yu_wei
 * @create: 2024/11/07 17:27
 */
@Mapper
public interface VisitLogMapper extends BaseMapper<VisitLog> {
    
    
    
    
    /**
     * 获取7天用户访问结果
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 用户访问结果
     */
    List<UserViewResp> selectUserViewList(@Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime);
    
    /**
     * 查询访问日志
     *
     * @param logQuery 访问日志查询条件
     * @return 访问日志列表
     */
    List<VisitLog> selectVisitLogList(@Param("param") LogQuery logQuery);
}

