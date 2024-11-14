package com.wy.wydemo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 任务状态枚举
 * @class: TaskStatusEnum
 * @author: yu_wei
 * @create: 2024/11/11 09:56
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {
    
    /**
     * 运行
     */
    RUNNING(0),
    
    /**
     * 暂停
     */
    PAUSE(1);
    
    /**
     * 状态
     */
    private final Integer status;
}
