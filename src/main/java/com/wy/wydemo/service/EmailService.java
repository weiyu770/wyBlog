package com.wy.wydemo.service;

import com.wy.wydemo.model.dto.MailDTO;

/**
 * @description: 邮件服务
 * @class: EmailService
 * @author: yu_wei
 * @create: 2024/11/04 11:08
 */
public interface EmailService {
    
    
    /**
     * 发送邮箱验证码
     * @param mailDTO
     */
    void sendSimpleMail(MailDTO mailDTO);
    
    
    /**
     * 发送html格式的邮件
     * @param mailDTO
     */
    void sendHtmlMail(MailDTO mailDTO);
}
