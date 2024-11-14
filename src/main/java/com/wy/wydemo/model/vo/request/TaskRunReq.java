package com.wy.wydemo.model.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 定时任务运行Request
 * @class: TaskRunReq
 * @author: yu_wei
 * @create: 2024/11/11 09:42
 */
@Data
@ApiModel(description = "定时任务运行Request")
public class TaskRunReq {
    
    /**
     * 任务id
     */
    @ApiModelProperty(value = "任务id")
    private Integer id;
    
    /**
     * 任务组别
     */
    @ApiModelProperty(value = "任务组别")
    private String taskGroup;
}