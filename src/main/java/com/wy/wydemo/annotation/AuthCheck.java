package com.wy.wydemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 权限校验
 * @class: AuthCheck
 * @author: yu_wei
 * @create: 2024/10/26 23:21
 */

//判断用户的role(角色)是否为管理员、用户、ban(封号)三种情况。
//创建用户这个方法就是只有管理员能够使用，用@AuthCheck自定义注解，然后写上使用该方法要有的权限即可。权限校验器因为是@Around环绕通知并且表明在有authCheck注解的方法周围执行如下逻辑。
//@Around环绕通知在也就是在方法执行前后额外添加的逻辑。AOP功能的诠释，如果有不懂这段逻辑的可以去回顾下Spring的AOP功能， 面试中经常会提及，能够回答出AOP的实际项目应用也是很不错的一个点。
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    
    /**
     * 必须有某个角色
     * @return
     */
    
    //管理员角色
   // String ADMIN_ROLE = "admin";
    String mustRole() default "";
    
}

