package com.wy.wydemo.service;

import com.wy.wydemo.model.vo.query.OnlineUserQuery;
import com.wy.wydemo.model.vo.request.EmailReq;
import com.wy.wydemo.model.vo.request.UserRoleReq;
import com.wy.wydemo.model.vo.response.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.User;
import com.wy.wydemo.model.vo.vo.LoginUserVO;
import com.wy.wydemo.model.vo.query.UserQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description: 用户服务
 * @class: UserService
 * @author: yu_wei
 * @create: 2024/10/26 17:27
 */
public interface UserService extends IService<User> {
    
    /**
     * 用户注册
     * @param userAccount   用户账号
     * @param passWord  用户密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String passWord);
    
    
    /**
     * 获取脱敏的已登录用户信息
     * @return
     */
    LoginUserVO getLoginUserVO(User user);
    


    


    
    /**
     * 查看后台登录用户信息
     *
     * @return 登录用户信息
     */
    UserBackInfoResp getUserBackInfo();
    
    
    /**
     * 查看登录用户菜单
     * @return 登录用户菜单
     */
    List<RouterResp> getUserMenu();
    
    /**
     * 查看后台用户列表
     *
     * @param userQuery 用户查询条件
     * @return {@link UserBackResp} 用户后台列表
     */
    PageResult<UserBackResp> listUserBackVO(UserQuery userQuery);
    
    /**
     * 修改用户
     *
     * @param user 用户信息
     */
    void updateUser(UserRoleReq user);
    
    /**
     * 查看在线用户
     * @param onlineUserQuery
     * @return
     */
    PageResult<OnlineUserResp> listOnlineUser(OnlineUserQuery onlineUserQuery);
    
    /**
     * 下线用户
     * @param token
     */
    void kickOutUser(String token);
    
    /**
     * 查看登录用户信息
     * @return
     */
    UserInfoResp getUserInfo();
    
    /**
     * 修改用户邮箱
     * @param email
     */
    void updateUserEmail(EmailReq email);
    
    /**
     * 查询角色列表
     * @return
     */
    List<UserRoleResp> listUserRoleDTO();
    
    
    /**
     * 修改用户头像
     * @param file
     * @return
     */
    String updateUserAvatar(MultipartFile file);
}
