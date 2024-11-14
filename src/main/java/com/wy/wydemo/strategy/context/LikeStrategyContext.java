package com.wy.wydemo.strategy.context;

import com.wy.wydemo.model.enums.LikeTypeEnum;
import com.wy.wydemo.strategy.LikeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description: 点赞策略上下文
 * @class: LikeStrategyContext
 * @author: yu_wei
 * @create: 2024/11/06 01:16
 */
@Service
public class LikeStrategyContext {
    
    @Autowired
    private Map<String, LikeStrategy> likeStrategyMap;
    
    /**
     * 点赞
     *
     * @param likeType 点赞类型
     * @param typeId   类型id
     */
    public void executeLikeStrategy(LikeTypeEnum likeType, Integer typeId) {
        likeStrategyMap.get(likeType.getStrategy()).like(typeId);
    }
}