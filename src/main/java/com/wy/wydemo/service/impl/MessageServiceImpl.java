package com.wy.wydemo.service.impl;

import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.constant.CommonConstant;
import com.wy.wydemo.mapper.MessageMapper;
import com.wy.wydemo.model.entity.Message;
import com.wy.wydemo.model.entity.SiteConfig;
import com.wy.wydemo.model.vo.query.MessageQuery;
import com.wy.wydemo.model.vo.request.CheckReq;
import com.wy.wydemo.model.vo.request.MessageReq;
import com.wy.wydemo.model.vo.response.MessageResp;
import com.wy.wydemo.model.vo.response.MessageBackResp;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.service.MessageService;
import com.wy.wydemo.utils.BeanCopyUtils;
import com.wy.wydemo.utils.HTMLUtils;
import com.wy.wydemo.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @class: MessageServiceImpl
 * @author: yu_wei
 * @create: 2024/11/08 15:40
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    
    @Autowired
    private MessageMapper messageMapper;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private SiteConfigService siteConfigService;
    
    
    /**
     * 查看留言列表
     * @return
     */
    @Override
    public List<MessageResp> listMessageVO() {
        // 查询留言列表
        return messageMapper.selectMessageVOList();
    }
    
    
    /**
     * 分页查看后台留言列表
     * @param messageQuery
     * @return
     */
    @Override
    public PageResult<MessageBackResp> listMessageBackVO(MessageQuery messageQuery) {
        // 查询留言数量
        Long count = messageMapper.selectCount(new LambdaQueryWrapper<Message>()
                .like(StringUtils.hasText(messageQuery.getKeyword()), Message::getNickname, messageQuery.getKeyword())
                .eq(Objects.nonNull(messageQuery.getIsCheck()), Message::getIsCheck, messageQuery.getIsCheck()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询后台友链列表
        List<MessageBackResp> messageBackRespList = messageMapper.selectBackMessageList(messageQuery);
        return new PageResult<>(messageBackRespList, count);
    }
    
    
    /**
     * 添加留言
     * @param message
     */
    @Override
    public void addMessage(MessageReq message) {
        SiteConfig siteConfig = siteConfigService.getSiteConfig();
        Integer messageCheck = siteConfig.getMessageCheck();
        String ipAddress = ServletUtil.getClientIP(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        Message newMessage = BeanCopyUtils.copyBean(message, Message.class);
        newMessage.setMessageContent(HTMLUtils.filter(message.getMessageContent()));
        newMessage.setIpAddress(ipAddress);
        newMessage.setIsCheck(messageCheck.equals(CommonConstant.FALSE) ? CommonConstant.TRUE : CommonConstant.FALSE);
        newMessage.setIpSource(ipSource);
        messageMapper.insert(newMessage);
    }
    
    
    /**
     * 审核留言
     * @param check
     */
    @Override
    public void updateMessageCheck(CheckReq check) {
        // 修改留言审核状态
        List<Message> messageList = check.getIdList()
                .stream()
                .map(id -> Message.builder()
                        .id(id)
                        .isCheck(check.getIsCheck())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(messageList);
    }
    
}
