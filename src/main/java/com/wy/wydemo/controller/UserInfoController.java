package com.wy.wydemo.controller;

/**
 * @description:
 * @class: UserInfoController
 * @author: yu_wei
 * @create: 2024/11/04 23:47
 */

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.wy.wydemo.model.vo.request.EmailReq;
import com.wy.wydemo.model.vo.response.UserInfoResp;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息控制器
 *
 * @author ican
 **/
@Api(tags = "用户信息")
@RestController
public class UserInfoController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 查询登录用户信息
     *
     * @return {@link UserInfoResp} 用户信息
     */
    @SaCheckLogin
    @ApiOperation(value = "查询登录用户信息")
    @GetMapping("/user/getUserInfo")
    public Result<UserInfoResp> getUserInfo() {
        return Result.success(userService.getUserInfo());
    }
    
    /**
     * 修改用户邮箱
     *
     * @param email 邮箱信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "修改用户邮箱")
    @SaCheckPermission(value = "user:email:update")
    @PutMapping("/user/email")
    public Result<?> updateUserEmail(@Validated @RequestBody EmailReq email) {
        userService.updateUserEmail(email);
        return Result.success();
    }
    
    //TODO 写一个修改用户个人信息简介的全流程
    //TODO 还有修改用户密码的
    
    
    /**
     * 修改用户头像
     *
     * @param file 文件
     * @return {@link Result<String>} 头像地址
     */
    @ApiOperation(value = "修改用户头像")
    @SaCheckPermission(value = "user:avatar:update")
    @PostMapping("/user/avatar")
    public Result<String> updateUserAvatar(@RequestParam(value = "file") MultipartFile file) {
        return Result.success(userService.updateUserAvatar(file));
    }
    
    
    
}
