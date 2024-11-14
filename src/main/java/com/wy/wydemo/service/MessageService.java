package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Message;
import com.wy.wydemo.model.vo.query.MessageQuery;
import com.wy.wydemo.model.vo.request.CheckReq;
import com.wy.wydemo.model.vo.request.MessageReq;
import com.wy.wydemo.model.vo.response.MessageResp;
import com.wy.wydemo.model.vo.response.MessageBackResp;
import com.wy.wydemo.model.vo.response.PageResult;

import java.util.List;

/**
 * @description:
 * @class: MessageService
 * @author: yu_wei
 * @create: 2024/11/08 15:39
 */
public interface MessageService extends IService<Message> {
    
    /**
     * 查看留言列表
     * @return
     */
    List<MessageResp> listMessageVO();
    
    
    /**
     * 分页查看后台留言列表
     * @param messageQuery
     * @return
     */
    PageResult<MessageBackResp> listMessageBackVO(MessageQuery messageQuery);
    
    
    /**
     * 添加留言
     * @param message
     */
    void addMessage(MessageReq message);
    
    
    /**
     * 审核留言
     * @param check
     */
    void updateMessageCheck(CheckReq check);
}
