package com.wy.wydemo.annotation;

import java.lang.annotation.*;

/**
 * @description: 访问日志注解
 * @class: VisitLogger
 * @author: yu_wei
 * @create: 2024/11/06 11:29
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitLogger {
    
    /**
     * @return 访问页面
     */
    String value() default "";
}
