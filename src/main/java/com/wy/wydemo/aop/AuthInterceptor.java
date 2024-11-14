//package com.wy.wydemo.aop;
//
//import com.wy.wydemo.annotation.AuthCheck;
//import com.wy.wydemo.exception.BusinessException;
//import com.wy.wydemo.model.entity.User;
//import com.wy.wydemo.model.enums.StatusCodeEnum;
//import com.wy.wydemo.model.enums.UserRoleEnum;
//import com.wy.wydemo.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//@Slf4j
//public class AuthInterceptor {
//
//    @Resource
//    private UserService userService;
//
//    /**
//     * 执行拦截
//     *
//     * @param joinPoint
//     * @param authCheck
//     * @return
//     */
//    @Around("@annotation(authCheck)")
//    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
//        String mustRole = authCheck.mustRole();
//        log.info("权限校验开始，要求权限: {}", mustRole);
//
//        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//
//        // 获取当前登录用户
//        User loginUser = userService.getLoginUser(request);
//        if (loginUser == null) {
//            log.warn("用户未登录，权限校验不通过");
//            throw new BusinessException(StatusCodeEnum.NO_AUTH_ERROR);
//        }
//        log.info("当前登录用户: {}, 用户角色: {}", loginUser.getId(), loginUser.getUserRole());
//
//        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
//
//        // 不需要特定权限，直接放行
//        if (mustRoleEnum == null) {
//            log.info("不需要特定权限，直接放行");
//            return joinPoint.proceed();
//        }
//
//        // 检查用户角色
//        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
//        if (userRoleEnum == null) {
//            log.warn("用户角色未定义，权限校验不通过");
//            throw new BusinessException(StatusCodeEnum.NO_AUTH_ERROR);
//        }
//
//        // 检查用户是否被封禁
//        if (UserRoleEnum.BAN.equals(userRoleEnum)) {
//            log.warn("用户已被封禁，权限校验不通过");
//            throw new BusinessException(StatusCodeEnum.NO_AUTH_ERROR);
//        }
//
//        // 检查管理员权限
//        if (UserRoleEnum.ADMIN.equals(mustRoleEnum)) {
//            if (!UserRoleEnum.ADMIN.equals(userRoleEnum)) {
//                log.warn("用户没有管理员权限，权限校验不通过");
//                throw new BusinessException(StatusCodeEnum.NO_AUTH_ERROR);
//            }
//            log.info("用户拥有管理员权限，权限校验通过");
//        } else {
//            log.info("用户权限符合要求，权限校验通过");
//        }
//
//        // 通过权限校验，放行
//        Object result = joinPoint.proceed();
//        log.info("权限校验结束，执行目标方法: {}", joinPoint.getSignature().getName());
//        return result;
//    }
//}
