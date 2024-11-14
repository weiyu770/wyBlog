package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 访问日志
 * @class: VisitLog
 * @author: yu_wei
 * @create: 2024/11/02 14:56
 */
@TableName("t_visit_log")
@Data
public class VisitLog {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 访问页面
     */
    private String page;
    
    /**
     * 访问ip
     */
    private String ipAddress;
    
    /**
     * 访问地址
     */
    private String ipSource;
    
    /**
     * 操作系统
     */
    private String os;
    
    /**
     * 浏览器
     */
    private String browser;
    
    /**
     * 访问时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
}