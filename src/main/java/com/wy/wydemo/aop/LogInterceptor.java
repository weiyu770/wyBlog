//package com.wy.wydemo.aop;
//
//import cn.hutool.core.date.StopWatch;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.UUID;
//
//@Aspect
//@Component
//@Slf4j
//public class LogInterceptor {
//
//    /**
//     * 执行拦截
//     */
//    @Around("execution(* com.wy.wydemo..controller.*.*(..))")
//    //拦截 com.wy.wydemo 包下的所有 controller 包中的所有方法。
//    //Around 注解表示“环绕通知”，即拦截器会在方法执行之前和之后都执行，能够记录请求开始和结束时的日志。
//
//    public Object doInterceptor(ProceedingJoinPoint point) throws Throwable {
//        // 开始计时
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        // 获取请求信息
//        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
//        String requestId = UUID.randomUUID().toString();
//        String url = httpServletRequest.getRequestURI();
//        String method = httpServletRequest.getMethod();
//        String ipAddress = httpServletRequest.getRemoteHost();
//
//        // 获取请求参数
//        Object[] args = point.getArgs();
//        String reqParam = "[" + StringUtils.join(args, ", ") + "]";
//
//        // 输出请求日志
//        log.info("请求开始 - ID: {}, 请求方式: {}, 请求路径: {}, 请求IP: {}, 请求参数: {}", requestId, method, url, ipAddress, reqParam);
//
//        // 记录结果和异常
//        Object result = null;
//        try {
//            result = point.proceed();
//            // 输出响应内容
//            log.info("请求成功 - ID: {}, 响应结果: {}", requestId, result);
//        } catch (Exception e) {
//            log.error("请求出错 - ID: {}, 错误信息: {}", requestId, e.getMessage(), e);
//            throw e;
//        } finally {
//            // 记录执行时间
//            stopWatch.stop();
//            long totalTimeMillis = stopWatch.getTotalTimeMillis();
//            log.info("请求结束 - ID: {}", requestId);
//            log.info("请求执行耗时: {} ms", totalTimeMillis);
//        }
//        return result;
//    }
//}
