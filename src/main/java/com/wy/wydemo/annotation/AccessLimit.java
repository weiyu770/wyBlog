package com.wy.wydemo.annotation;

import java.lang.annotation.*;

/**
 * @description: Redis限流注解 限制接口调用频率 AOP限流
 * @class: AccessLimit
 * @author: yu_wei
 * @create: 2024/11/04 11:03
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    
    /**
     * 限制周期(秒)
     */
    int seconds();
    
    /**
     * 规定周期内限制次数
     */
    int maxCount();
    
    /**
     * 触发限制时的消息提示
     */
    String msg() default "操作过于频繁请稍后再试";
    
}
