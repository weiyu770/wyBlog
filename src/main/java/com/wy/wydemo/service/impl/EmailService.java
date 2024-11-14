package com.wy.wydemo.service.impl;

import com.wy.wydemo.model.dto.MailDTO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.thymeleaf.TemplateEngine;
/**
 * @description: 邮箱服务实现类
 * @class: EmailService
 * @author: yu_wei
 * @create: 2024/11/04 11:10
 */
@Slf4j
@Service
public class EmailService implements com.wy.wydemo.service.EmailService {
    
    
    /**
     * 邮箱账号
     */
    private String email="3658043236@qq.com";// TODO 先写死
    
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Resource
    private TemplateEngine templateEngine;
    
    public void sendSimpleMail(MailDTO mailDTO) {
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom(email);
        simpleMail.setTo(mailDTO.getToEmail());
        simpleMail.setSubject(mailDTO.getSubject());
        simpleMail.setText(mailDTO.getContent());
        javaMailSender.send(simpleMail);
    }
    
    public void sendHtmlMail(MailDTO mailDTO) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            Context context = new Context();
            context.setVariables(mailDTO.getContentMap());
            String process = templateEngine.process(mailDTO.getTemplate(), context);
            mimeMessageHelper.setFrom(email);
            mimeMessageHelper.setTo(mailDTO.getToEmail());
            mimeMessageHelper.setSubject(mailDTO.getSubject());
            mimeMessageHelper.setText(process, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("sendHtmlMail fail, {}", e.getMessage());
        }
    }
    
}
