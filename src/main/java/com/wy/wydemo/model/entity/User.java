package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 用户实体类
 * @class: User
 * @author: yu_wei
 * @create: 2024/10/26 16:41
 */
@TableName("t_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 用户id 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
     * 用户昵称/用户账号 可以是账号或者昵称
     */
    @TableField("nick_name")
    private String nickName;
    
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    
    /**
     * 密码
     */
    @TableField("password")
    private String passWord;
    
    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;
    
    /**
     * 个人网站
     */
    @TableField("web_site")
    private String webSite;
    
    /**
     * 个人介绍
     */
    @TableField("intro")
    private String intro;
    
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    
    /**
     * 登录ip
     */
    @TableField("ip_address")
    private String ipAddress;
    
    /**
     * 登录地址
     */
    @TableField("ip_source")
    private String ipSource;
    
    /**
     * 登录方式 (1邮箱 2QQ 3Gitee 4Github 5手动注册)
     */
    @TableField("login_type")
    private Integer loginType;
    
    /**
     * 是否禁用 (0否 1是)
     */
    @TableField("is_disable")
    private Integer isDisable;
    
    /**
     * 登录时间
     */
    @TableField("login_time")
    private LocalDateTime loginTime;
    
    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
    
}
