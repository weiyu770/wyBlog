package com.wy.wydemo.aop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.wydemo.constant.PageConstant;
import com.wy.wydemo.utils.PageUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @description: 分页拦截器
 * @class: PageableInterceptor
 * @author: yu_wei
 * @create: 2024/11/04 17:01
 */
public class PageableInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        String currentPage = request.getParameter(PageConstant.CURRENT);
        String pageSize = Optional.ofNullable(request.getParameter(PageConstant.SIZE))
                .orElse(PageConstant.DEFAULT_SIZE);
        if (StringUtils.hasText(currentPage)) {
            PageUtils.setCurrentPage(new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize)));
        }
        return true;
    }
    
    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
        PageUtils.remove();
    }
    
}