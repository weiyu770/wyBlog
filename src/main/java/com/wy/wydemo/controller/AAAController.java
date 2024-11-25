package com.wy.wydemo.controller;

import com.wy.wydemo.model.vo.request.LoginReq;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.LoginService;
import com.wy.wydemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @class: AAAController
 * @author: yu_wei
 * @create: 2024/11/25 22:09
 */


@Api(tags = "AAAAA")
@RestController
@Slf4j
public class AAAController {
    
    @Resource
    private UserService userService;
    
    @Resource
    private LoginService loginService;
    
    
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
    
}
