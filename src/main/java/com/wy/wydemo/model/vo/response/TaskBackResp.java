package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 定时任务后台Response
 * @class: TaskBackResp
 * @author: yu_wei
 * @create: 2024/11/11 09:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "定时任务后台Response")
public class TaskBackResp {
    
    /**
     * 任务id
     */
    @ApiModelProperty(value = "任务id")
    private Integer id;
    
    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String taskName;
    
    /**
     * 任务组名
     */
    @ApiModelProperty(value = "任务组名")
    private String taskGroup;
    
    /**
     * 调用目标
     */
    @ApiModelProperty(value = "调用目标")
    private String invokeTarget;
    
    /**
     * cron执行表达式
     */
    @ApiModelProperty(value = "cron执行表达式")
    private String cronExpression;
    
    /**
     * 计划执行错误策略 (1立即执行 2执行一次 3放弃执行)
     */
    @ApiModelProperty(value = "计划执行错误策略 (1立即执行 2执行一次 3放弃执行)")
    private Integer misfirePolicy;
    
    /**
     * 是否并发执行 (0否 1是)
     */
    @ApiModelProperty(value = "是否并发执行 (0否 1是)")
    private Integer concurrent;
    
    /**
     * 任务状态 (0运行 1暂停)
     */
    @ApiModelProperty(value = "任务状态 (0运行 1暂停)")
    private Integer status;
    
    /**
     * 任务备注信息
     */
    @ApiModelProperty(value = "任务备注信息")
    private String remark;
    
    /**
     * 下次执行时间
     */
    @ApiModelProperty(value = "下次执行时间")
    private Date nextValidTime;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
}
