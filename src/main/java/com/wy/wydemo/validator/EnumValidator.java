package com.wy.wydemo.validator;

import com.wy.wydemo.annotation.EnumValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 枚举类型校验器
 * @class: EnumValidator
 * @author: yu_wei
 * @create: 2024/11/07 23:57
 */
public class EnumValidator implements ConstraintValidator<EnumValid, Integer> {
    
    private final Set<Integer> set = new HashSet<>();
    
    /**
     * 初始化
     *
     * @param constraintAnnotation 评论类型注解
     */
    @Override
    public void initialize(EnumValid constraintAnnotation) {
        for (int value : constraintAnnotation.values()) {
            set.add(value);
        }
    }
    
    /**
     * 校验方法
     *
     * @param value                      校验的值
     * @param constraintValidatorContext 上下文
     * @return 是否校验成功
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(value);
    }
}