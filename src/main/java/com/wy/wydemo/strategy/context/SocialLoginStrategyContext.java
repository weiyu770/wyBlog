package com.wy.wydemo.strategy.context;

import com.wy.wydemo.model.enums.LoginTypeEnum;
import com.wy.wydemo.model.vo.request.CodeReq;
import com.wy.wydemo.strategy.SocialLoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description: 登录策略上下文
 * @class: SocialLoginStrategyContext
 * @author: yu_wei
 * @create: 2024/11/04 15:14
 */
@Service
public class SocialLoginStrategyContext {
    
    @Autowired
    private Map<String, SocialLoginStrategy> socialLoginStrategyMap;
    
    /**
     * 登录
     *
     * @param loginTypeEnum 登录枚举
     * @return  Token
     */
    public String executeLoginStrategy(CodeReq data, LoginTypeEnum loginTypeEnum) {
        return socialLoginStrategyMap.get(loginTypeEnum.getStrategy()).login(data);
    }
}
