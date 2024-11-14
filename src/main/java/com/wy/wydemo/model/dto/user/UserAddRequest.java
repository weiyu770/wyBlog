package com.wy.wydemo.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @class: UserAddRequest
 * @author: yu_wei
 * @create: 2024/10/26 23:39
 */
@Data
public class UserAddRequest implements Serializable {
    
    /**
     * 用户昵称
     */
    private String userName;
    
    /**
     * 账号
     */
    private String userAccount;
    
    /**
     * 用户头像
     */
    private String userAvatar;
    
    /**
     * 用户角色: user, admin
     */
    private String userRole;
    
    private static final long serialVersionUID = 1L;
}