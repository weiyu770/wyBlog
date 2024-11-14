package com.wy.wydemo.model.vo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户视图（脱敏）
 * @class: UserVO
 * @author: yu_wei
 * @create: 2024/10/27 12:31
 */
@Data
public class UserVO implements Serializable {
    
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
     * 用户简介
     */
    private String userProfile;
    
    /**
     * 用户角色：user/admin/ban
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String userRole;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    private static final long serialVersionUID = 1L;
}