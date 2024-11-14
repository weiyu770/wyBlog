package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 定时任务日志实体类
 * @class: TaskLog
 * @author: yu_wei
 * @create: 2024/11/02 14:55
 */
@TableName("t_task_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskLog {
    
    /**
     * 任务日志id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 任务名称
     */
    private String taskName;
    
    /**
     * 任务组名
     */
    private String taskGroup;
    
    /**
     * 调用目标字符串
     */
    private String invokeTarget;
    
    /**
     * 日志信息
     */
    private String taskMessage;
    
    /**
     * 执行状态 (0失败 1正常)
     */
    private Integer status;
    
    /**
     * 错误信息
     */
    private String errorInfo;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
}