package com.wy.wydemo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 搜索模式枚举
 * @class: SearchModeEnum
 * @author: yu_wei
 * @create: 2024/11/06 11:04
 */
@Getter
@AllArgsConstructor
public enum SearchModeEnum {
    
    /**
     * mysql
     */
    MYSQL("mysql", "mySqlSearchStrategyImpl"),
    
    /**
     * elasticsearch
     */
    ELASTICSEARCH("elasticsearch", "esSearchStrategyImpl");
    
    /**
     * 模式
     */
    private final String mode;
    
    /**
     * 策略
     */
    private final String strategy;
    
    /**
     * 获取策略
     *
     * @param mode 模式
     * @return {@link String} 搜索策略
     */
    public static String getStrategy(String mode) {
        for (SearchModeEnum value : SearchModeEnum.values()) {
            if (value.getMode().equals(mode)) {
                return value.getStrategy();
            }
        }
        return null;
    }
}