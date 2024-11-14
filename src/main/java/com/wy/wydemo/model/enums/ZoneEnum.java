package com.wy.wydemo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 时区枚举
 * @class: ZoneEnum
 * @author: yu_wei
 * @create: 2024/11/04 17:07
 */
@Getter
@AllArgsConstructor
public enum ZoneEnum {
    
    /**
     * 上海
     */
    SHANGHAI("Asia/Shanghai", "中国上海");
    
    /**
     * 时区
     */
    private final String zone;
    
    /**
     * 描述
     */
    private final String description;
    
}
