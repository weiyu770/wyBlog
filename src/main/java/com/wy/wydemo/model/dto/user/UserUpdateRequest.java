package com.wy.wydemo.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户信息更新请求
 * @class: UserUpdateRequest
 * @author: yu_wei
 * @create: 2024/10/27 00:03
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;
    
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
    
    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;
    
    private static final long serialVersionUID = 1L;
}