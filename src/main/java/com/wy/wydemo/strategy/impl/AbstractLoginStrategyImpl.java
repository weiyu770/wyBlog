package com.wy.wydemo.strategy.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wy.wydemo.mapper.UserMapper;
import com.wy.wydemo.mapper.UserRoleMapper;
import com.wy.wydemo.model.dto.SocialTokenDTO;
import com.wy.wydemo.model.dto.SocialUserInfoDTO;
import com.wy.wydemo.model.entity.User;
import com.wy.wydemo.model.entity.UserRole;
import com.wy.wydemo.model.enums.RoleEnum;
import com.wy.wydemo.model.vo.request.CodeReq;
import com.wy.wydemo.strategy.SocialLoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @description: 抽象登录模块
 * @class: AbstractLoginStrategyImpl
 * @author: yu_wei
 * @create: 2024/11/04 15:34
 */
@Service
public abstract class AbstractLoginStrategyImpl implements SocialLoginStrategy {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Override
    public String login(CodeReq data) {
        User user;
        // 获取token
        SocialTokenDTO socialToken = getSocialToken(data);
        // 获取用户信息
        SocialUserInfoDTO socialUserInfoDTO = getSocialUserInfo(socialToken);
        // 判断是否已注册
        User existUser = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getId)
                .eq(User::getUserName, socialUserInfoDTO.getId())
                .eq(User::getLoginType, socialToken.getLoginType()));
        // 用户未登录过
        if (Objects.isNull(existUser)) {
            user = saveLoginUser(socialToken, socialUserInfoDTO);
        } else {
            user = existUser;
        }
        // 校验指定账号是否已被封禁，如果被封禁则抛出异常 `DisableServiceException`
        StpUtil.checkDisable(user.getId());
        // 通过校验后，再进行登录
        StpUtil.login(user.getId());
        return StpUtil.getTokenValue();
    }
    
    /**
     * 获取第三方Token
     *
     * @param data data
     * @return  SocialTokenDTO}第三方token
     */
    public abstract SocialTokenDTO getSocialToken(CodeReq data);
    
    /**
     * 获取第三方用户信息
     *
     * @param socialToken 第三方token
     * @return  SocialUserInfoDTO 第三方用户信息
     */
    public abstract SocialUserInfoDTO getSocialUserInfo(SocialTokenDTO socialToken);
    
    /**
     * 新增用户账号
     *
     * @param socialToken 第三方Token
     * @return  User 登录用户身份权限
     */
    private User saveLoginUser(SocialTokenDTO socialToken, SocialUserInfoDTO socialUserInfoDTO) {
        // 新增用户信息
        User newUser = User.builder()
                .avatar(socialUserInfoDTO.getAvatar())
                .userName(socialUserInfoDTO.getId())
                .passWord(socialToken.getAccessToken())
                .loginType(socialToken.getLoginType())
                .build();
        userMapper.insert(newUser);
        // 新增用户角色
        UserRole userRole = UserRole.builder()
                .userId(String.valueOf(newUser.getId()))
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
        return newUser;
    }
    
}
