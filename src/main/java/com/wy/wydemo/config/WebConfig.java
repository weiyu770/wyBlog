package com.wy.wydemo.config;

import com.wy.wydemo.aop.AccessLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Redis限流注册配置拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AccessLimitInterceptor accessLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimitInterceptor)
                .addPathPatterns("/**");  // 拦截所有请求路径，或指定路径
    }
}
