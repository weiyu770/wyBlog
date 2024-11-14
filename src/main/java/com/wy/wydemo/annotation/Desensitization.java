package com.wy.wydemo.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wy.wydemo.annotation.serialize.DesensitizationSerialize;
import com.wy.wydemo.model.enums.DesensitizationTypeEnum;

import java.lang.annotation.*;

/**
 * @description: 脱敏注解
 * @class: Desensitization
 * @author: yu_wei
 * @create: 2024/11/04 18:25
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationSerialize.class)
public @interface Desensitization {
    
    /**
     * 脱敏数据类型，在MY_RULE的时候，startInclude和endExclude生效
     */
    DesensitizationTypeEnum type() default DesensitizationTypeEnum.MY_RULE;
    
    /**
     * 脱敏开始位置（包含）
     */
    int startInclude() default 0;
    
    /**
     * 脱敏结束位置（不包含）
     */
    int endExclude() default 0;
    
}
