package com.wy.wydemo.satoken;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wy.wydemo.mapper.UserMapper;
import com.wy.wydemo.model.entity.User;
import com.wy.wydemo.model.vo.response.OnlineUserResp;
import com.wy.wydemo.utils.IpUtils;
import com.wy.wydemo.utils.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import static com.wy.wydemo.constant.CommonConstant.ONLINE_USER;
import static com.wy.wydemo.model.enums.ZoneEnum.SHANGHAI;

/**
 * @description: 自定义侦听器的实现
 * @class: MySaTokenListener
 * @author: yu_wei
 * @create: 2024/11/04 16:59
 */
@Component
public class MySaTokenListener implements SaTokenListener {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private HttpServletRequest request;
    
    /**
     * 每次登录时触发
     */
    //TODO
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        // 查询用户昵称
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getNickName)
                .eq(User::getId, loginId));
        // 解析browser和os
        Map<String, String> userAgentMap = UserAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));
        // 获取登录ip地址
        String ipAddress = ServletUtil.getClientIP(request);
        // 获取登录地址
        String ipSource = IpUtils.getIpSource(ipAddress);
        // 获取登录时间
        LocalDateTime loginTime = LocalDateTime.now(ZoneId.of(SHANGHAI.getZone()));
        OnlineUserResp onlineUserRequest = OnlineUserResp.builder()
                .id(Long.valueOf(String.valueOf(loginId)))
                .token(tokenValue)
                //TODO
                .nickname(user != null ? user.getNickName() : "")
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .os(userAgentMap.get("os"))
                .browser(userAgentMap.get("browser"))
                .loginTime(loginTime)
                .build();
        // 更新用户登录信息
        User newUser = User.builder()
                .id(Long.valueOf(String.valueOf(loginId)))
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .loginTime(loginTime)
                .build();
        userMapper.updateById(newUser);
        // 用户在线信息存入tokenSession
        SaSession tokenSession = StpUtil.getTokenSessionByToken(tokenValue);
        tokenSession.set(ONLINE_USER, onlineUserRequest);
    }
    
    /**
     * 每次注销时触发
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        // 删除缓存中的用户信息
        StpUtil.logoutByTokenValue(tokenValue);
    }
    
    /**
     * 每次被踢下线时触发
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
    }
    
    /**
     * 每次被顶下线时触发
     */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
    
    }
    
    /**
     * 每次被封禁时触发
     */
    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
    
    }
    
    /**
     * 每次被解封时触发
     */
    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
    
    }
    
    /**
     * 每次二级认证时触发
     */
    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {
    
    }
    
    /**
     * 每次退出二级认证时触发
     */
    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {
    
    }
    
    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCreateSession(String id) {
    
    }
    
    /**
     * 每次注销Session时触发
     */
    @Override
    public void doLogoutSession(String id) {
    
    }
    
    /**
     * 每次Token续期时触发
     */
    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
    
    }
}
