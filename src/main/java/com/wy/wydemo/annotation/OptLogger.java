package com.wy.wydemo.annotation;

import java.lang.annotation.*;

/**
 * @description:  操作日志注解
 * @class: OptLogger
 * @author: yu_wei
 * @create: 2024/11/04 21:01
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OptLogger {
    
    /**
     * @return 操作描述
     */
    String value() default "";
    
}
