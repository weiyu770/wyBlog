package com.wy.wydemo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.wy.wydemo.annotation.OptLogger;
import com.wy.wydemo.model.vo.response.*;
import com.wy.wydemo.model.vo.response.RouterResp;
import com.wy.wydemo.model.vo.request.UserRoleReq;
import com.wy.wydemo.model.vo.query.UserQuery;
import com.wy.wydemo.model.vo.query.OnlineUserQuery;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.wy.wydemo.constant.OptTypeConstant.KICK;
import static com.wy.wydemo.constant.OptTypeConstant.UPDATE;

/**
 * @description: 用户接口
 * @class: UserController
 * @author: yu_wei
 * @create: 2024/10/26 17:33
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    
    @Resource
    private UserService userService;
    
    
    /**
     * 查询后台登录用户信息
     *
     * @return 登录用户信息
     */
    @ApiOperation(value = "查询后台用户信息")
    @GetMapping("/admin/user/getUserInfo")
    public Result<UserBackInfoResp> getUserBackInfo() {
        return Result.success(userService.getUserBackInfo());
    }
    
    /**
     * 查询登录用户菜单
     *
     * @return 登录用户菜单
     */
    @ApiOperation(value = "查询登录用户")
    @GetMapping("/admin/user/getUserMenu")
    public Result<List<RouterResp>> getUserMenu() {
        return Result.success(userService.getUserMenu());
    }
    
    
    /**
     * 查询后台用户列表
     *
     * @param userQuery 用户查询条件
     * @return {@link UserBackResp} 用户后台列表
     */
    @ApiOperation(value = "查询后台用户列表")
//    @SaCheckPermission("system:user:list") //管理员权限
    @GetMapping("/admin/user/list")
    public Result<PageResult<UserBackResp>> listUserBackVO(UserQuery userQuery) {
        return Result.success(userService.listUserBackVO(userQuery));
    }
    
    
      /**
     * 查看用户角色选项
     *
     * @return {@link UserRoleResp} 用户角色选项
     */
    @ApiOperation(value = "查看用户角色选项")
    @SaCheckPermission("system:user:list")
    @GetMapping("/admin/user/role")
    public Result<List<UserRoleResp>> listUserRoleDTO() {
        return Result.success(userService.listUserRoleDTO());
    }
    
    
    
    /**
     * 修改用户角色
     *
     * @param user 用户信息
     * @return {@link Result<>}
     */
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改用户")
//    @SaCheckPermission("system:user:update")
    @PutMapping("/admin/user/update")
    public Result<?> updateUser(@Validated @RequestBody UserRoleReq user) {
        userService.updateUser(user);
        return Result.success();
    }
    
    /**
     * 查看在线用户
     *
     * @param onlineUserQuery 查询条件
     * @return {@link OnlineUserResp} 在线用户列表
     */
    @ApiOperation(value = "查看在线用户")
//    @SaCheckPermission("monitor:online:list")
    @GetMapping("/admin/online/list")
    public Result<PageResult<OnlineUserResp>> listOnlineUser(OnlineUserQuery onlineUserQuery) {
        return Result.success(userService.listOnlineUser(onlineUserQuery));
    }
    
    
    /**
     * 下线用户
     *
     * @param token 在线Token
     * @return {@link Result<>}
     */
    @OptLogger(value = KICK)
    @ApiOperation(value = "下线用户")
//    @SaCheckPermission("monitor:online:kick")
    @GetMapping("/admin/online/kick/{token}")
    public Result<?> kickOutUser(@PathVariable("token") String token) {
        userService.kickOutUser(token);
        return Result.success();
    }
    
    
    
    
    
    
}