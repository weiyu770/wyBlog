package com.wy.wydemo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @description: 登录方式枚举，包含多种登录策略及描述
 * @class: LoginTypeEnum
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    
    /**
     * 邮箱
     */
    EMAIL(1, "邮箱登录", ""),
    
    /**
     * QQ
     */
    QQ(2, "QQ登录", "qqLoginStrategyImpl"),
    
    /**
     * Gitee
     */
    GITEE(3, "Gitee登录", "giteeLoginStrategyImpl"),
    
    /**
     * Github
     */
    GITHUB(4, "Github登录", "githubLoginStrategyImpl"),
    
    /**
     * 默认登录方式，用于后台或数据库配置
     */
    DEFAULT(5, "默认登录方式", "defaultLoginStrategyImpl");
    
    /**
     * 登录方式的标识，用于区分不同方式
     */
    private final Integer loginType;
    
    /**
     * 描述登录方式
     */
    private final String description;
    
    /**
     * 策略类名称，用于找到对应的策略实现
     */
    private final String strategy;
    
    /**
     * 根据 loginType 查找对应的枚举实例
     *
     * @param loginType 登录方式标识
     * @return 对应的 LoginTypeEnum 实例
     */
    public static LoginTypeEnum getByLoginType(Integer loginType) {
        return Arrays.stream(values())
                .filter(type -> type.getLoginType().equals(loginType))
                .findFirst()
                .orElse(null);
    }
}
