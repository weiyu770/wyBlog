package com.wy.wydemo.controller;

import com.wy.wydemo.annotation.AccessLimit;
import com.wy.wydemo.exception.BusinessException;
import com.wy.wydemo.model.dto.user.UserRegisterRequest;
import com.wy.wydemo.model.enums.StatusCodeEnum;
import com.wy.wydemo.model.vo.request.CodeReq;
import com.wy.wydemo.model.vo.request.LoginReq;
import com.wy.wydemo.model.vo.request.UserRegistrationReq;
import com.wy.wydemo.model.vo.vo.UserLoginResponseVO;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.LoginService;
import com.wy.wydemo.service.UserService;
import com.wy.wydemo.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @description:
 * @class: LoginController
 * @author: yu_wei
 * @create: 2024/11/02 15:36
 */
@Api(tags = "注册管理")
@RestController @Slf4j
public class LoginController {
    
    
    @Resource
    private UserService userService;
    
    @Resource
    private LoginService loginService;
    
    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册 后台测试")
    public Result<Long> userRegister(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        
        if(userRegisterRequest == null){
            //    PARAMS_ERROR(40000, "请求参数错误"),
            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR);
        }
        String userName = userRegisterRequest.getUserName();
        String passWord = userRegisterRequest.getPassWord();
        if (StringUtils.isAnyBlank(userName, passWord)) {
            return null;
        }
        long result = userService.userRegister(userName, passWord);
        return ResultUtils.success(result);
    }
    
    
    /**
     * 用户登录
     *
     * @param login 登录参数
     * @return {@link String} Token
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody LoginReq login) {
        return Result.success(loginService.login(login));
    }
    
    
    /**
     * 发送邮箱验证码 用邮箱 密码 验证码登录
     *
     * @return {@link Result<>}
     */
    //TODO优化
    @AccessLimit(seconds = 60, maxCount = 1)
    @ApiOperation(value = "发送邮箱验证码")
    @GetMapping("/code")
    //username是接收者 用户要输入自己的邮箱
    public Result<?> sendCode(String username) {
        loginService.sendCode(username);
        return Result.success();
    }
    
    /**
     * 用户邮箱注册
     *
     * @param userRegistrationReq 注册信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "用户邮箱注册")
    @PostMapping("/register/email")
    public Result<?> register(@Validated @RequestBody UserRegistrationReq userRegistrationReq) {
        loginService.register(userRegistrationReq);
        return Result.success();
    }
    
    /**
     * QQ登录
     *
     * @return  Result<String> Token
     */
    @ApiOperation(value = "QQ登录")
    @PostMapping("/oauth/qq")
    public Result<String> QQLogin(@Validated @RequestBody CodeReq codeRequest) {
        return Result.success(loginService.QQLogin(codeRequest));
    }
    
    
}
