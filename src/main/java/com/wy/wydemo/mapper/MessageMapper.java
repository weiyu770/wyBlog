package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Message;
import com.wy.wydemo.model.vo.query.MessageQuery;
import com.wy.wydemo.model.vo.response.MessageResp;
import com.wy.wydemo.model.vo.response.MessageBackResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: MessageMapper
 * @author: yu_wei
 * @create: 2024/11/07 17:27
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    
    
    
    /**
     * 查询留言列表
     *
     * @return 留言列表
     */
    List<MessageResp> selectMessageVOList();
    
    /**
     * 查询后台留言列表
     *
     * @param messageQuery 留言查询条件
     * @return 后台留言列表
     */
    List<MessageBackResp> selectBackMessageList(@Param("param") MessageQuery messageQuery);
}
