package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 定时任务
 * @class: Task
 * @author: yu_wei
 * @create: 2024/11/02 14:53
 */
@TableName("t_task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    
    /**
     * 任务id
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
     * 调用目标
     */
    private String invokeTarget;
    
    /**
     * cron执行表达式
     */
    private String cronExpression;
    
    /**
     * 计划执行错误策略 (1立即执行 2执行一次 3放弃执行)
     */
    private Integer misfirePolicy;
    
    /**
     * 是否并发执行 (0否 1是)
     */
    private Integer concurrent;
    
    /**
     * 任务状态 (0运行 1暂停)
     */
    private Integer status;
    
    /**
     * 任务备注信息
     */
    private String remark;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
}