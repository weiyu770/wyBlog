package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 朋友列表/友链实体类
 * @class: Friend
 * @author: yu_wei
 * @create: 2024/11/02 14:21
 */
@Data
@TableName("t_friend")
public class Friend {
    
    /**
     * 友链id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 友链名称
     */
    private String name;
    
    /**
     * 友链颜色
     */
    private String color;
    
    /**
     * 友链头像
     */
    private String avatar;
    
    /**
     * 友链地址
     */
    private String url;
    
    /**
     * 友链介绍
     */
    private String introduction;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
}