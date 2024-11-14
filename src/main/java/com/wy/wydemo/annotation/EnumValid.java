package com.wy.wydemo.annotation;

import com.wy.wydemo.validator.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @description: 评论类型注解
 * @class: EnumValid
 * @author: yu_wei
 * @create: 2024/11/07 23:56
 */
@Documented
@Constraint(validatedBy = {EnumValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValid {
    
    String message() default "{javax.validation.constraints.NotBlank.message}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    /**
     * @return 评论类型
     */
    int[] values() default {};
    
}