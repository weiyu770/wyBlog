package com.wy.wydemo.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户更新个人信息请求
 * @class: UserUpdateMyRequest
 * @author: yu_wei
 * @create: 2024/10/27 12:28
 */
@Data
public class UserUpdateMyRequest implements Serializable {
    
    /**
     * 用户昵称
     */
    private String userName;
    
    /**
     * 用户头像
     */
    private String userAvatar;
    
    /**
     * 简介
     */
    private String userProfile;
    
    private static final long serialVersionUID = 1L;
}