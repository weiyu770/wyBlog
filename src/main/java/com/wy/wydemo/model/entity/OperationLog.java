package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description: 操作日志实体类
 * @class: OperationLog
 * @author: yu_wei
 * @create: 2024/11/02 14:26
 */
@TableName("t_operation_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLog {
    /**
     * 操作id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 操作模块
     */
    private String module;
    
    /**
     * 操作类型
     */
    private String type;
    
    /**
     * 操作uri
     */
    private String uri;
    
    /**
     * 方法名称
     */
    private String name;
    
    /**
     * 操作描述
     */
    private String description;
    
    /**
     * 请求参数
     */
    private String params;
    
    /**
     * 请求方式
     */
    private String method;
    
    /**
     * 返回数据
     */
    private String data;
    
    /**
     * 用户id
     */
    private Integer userId;
    
    /**
     * 用户昵称
     */
    private String nickname;
    
    /**
     * 操作ip
     */
    private String ipAddress;
    
    /**
     * 操作地址
     */
    private String ipSource;
    
    /**
     * 操作耗时 (毫秒)
     */
    private long times;
    
    /**
     * 操作时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}