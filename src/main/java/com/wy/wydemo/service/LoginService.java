package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.User;
import com.wy.wydemo.model.vo.request.CodeReq;
import com.wy.wydemo.model.vo.request.LoginReq;
import com.wy.wydemo.model.vo.request.UserRegistrationReq;

/**
 * @description: 用户登录服务
 * @class: LoginService
 * @author: yu_wei
 * @create: 2024/11/02 17:15
 */
public interface LoginService extends IService<User> {
    
    /**
     * 用户登录
     *
     * @param loginReq
     * @param
     * @return
     */
    String login(LoginReq loginReq);
    
    /**
     * 发送邮箱验证码
     * @param username
     */
    void sendCode(String username);
    
    
    /**
     * 用户邮箱注册
     * @param userRegistrationReq
     */
    void register(UserRegistrationReq userRegistrationReq);
    
    /**
     * QQ登录
     * @param codeRequest
     * @return
     */
    String QQLogin(CodeReq codeRequest);
    
}
