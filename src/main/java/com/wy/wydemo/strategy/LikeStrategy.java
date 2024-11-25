package com.wy.wydemo.strategy;

/**
 * @description: 点赞策略
 * @class: LikeStrategy
 * @author: yu_wei
 * @create: 2024/11/11 09:09
 */
public interface LikeStrategy {
    
    /**
     * 点赞
     *
     * @param typeId 类型id
     */
    void like(Integer typeId);
    
    /**
     * 取消点赞
     *
     * @param typeId 类型id
     */
    void unlike(Integer typeId);
}
