package com.wy.wydemo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.constant.CommonConstant;
import com.wy.wydemo.constant.RedisConstant;
import com.wy.wydemo.exception.BusinessException;
import com.wy.wydemo.mapper.UserMapper;
import com.wy.wydemo.mapper.UserRoleMapper;
import com.wy.wydemo.model.dto.MailDTO;
import com.wy.wydemo.model.entity.SiteConfig;
import com.wy.wydemo.model.entity.User;
import com.wy.wydemo.model.entity.UserRole;
import com.wy.wydemo.model.enums.LoginTypeEnum;
import com.wy.wydemo.model.enums.RoleEnum;
import com.wy.wydemo.model.enums.StatusCodeEnum;
import com.wy.wydemo.model.vo.request.CodeReq;
import com.wy.wydemo.model.vo.request.LoginReq;
import com.wy.wydemo.model.vo.request.UserRegistrationReq;
import com.wy.wydemo.model.vo.vo.UserLoginResponseVO;
import com.wy.wydemo.service.EmailService;
import com.wy.wydemo.service.LoginService;
import com.wy.wydemo.strategy.context.SocialLoginStrategyContext;
import com.wy.wydemo.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


/**
 * @description:
 * @class: LoginServiceImpl
 * @author: yu_wei
 * @create: 2024/11/02 17:19
 */
@Slf4j
@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {
    
    
    

    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private EmailService emailService;
    
    //TODO 线程池
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    
    @Resource
    private RedisService redisService;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    
    @Autowired
    private SocialLoginStrategyContext socialLoginStrategyContext;
    
    
    /**
     * 用户登录
     *
     * @param loginReq
     * @return
     */
    @Override
    public String login(LoginReq loginReq) {
        
        if (loginReq == null) {
            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR);
        }
        String userName = loginReq.getUserName();
        String passWord = loginReq.getPassWord();
        if (StringUtils.isAnyBlank(userName, passWord)) {
        }
        
        // 1. 校验
        if (StringUtils.isAnyBlank(userName, passWord)) {
            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR, "参数为空");
        }
        if (userName.length() < 4) {
            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR, "账号过短 账号长度要大于4位");
        }
        if (passWord.length() < 6) {
            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR, "密码过短 密码长度要大于6位");
        }
        // 2. 加密
        String encryptPassword = SecurityUtils.sha256Encrypt(passWord);
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("password", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            Assert.notNull(user, "用户不存在或密码错误");
            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR, "用户不存在或密码错误");
        }
         // 校验指定账号是否已被封禁，如果被封禁则抛出异常 `DisableServiceException`
        StpUtil.checkDisable(user.getId());
        // 通过校验后，再进行登录
        //如果登录成功 StpUtil.login(user.getId())
        StpUtil.login(user.getId());
        if (StringUtils.isNotBlank(String.valueOf(user.getId()))) {
            log.info("登录成功 当前用户账号为：{}", user.getUserName());
        }
        String tokenValue = StpUtil.getTokenValue();
        log.info("用户登录成功 当前Token为：{}", tokenValue);
        long timeout = StpUtil.getTokenTimeout();  // 获取当前 token 剩余的有效时间，单位为秒
        System.out.println("当前 Token 剩余有效期为：" + timeout + " 秒");
        String token = StpUtil.getTokenValue();
        return token;
    }
    
    
    //获取脱敏的已登录用户信息
    //用户前端视图
    public UserLoginResponseVO getUserLoginVO(User user) {
        if (user == null) {
            return null;
        }
        UserLoginResponseVO userLoginResponseVO=new UserLoginResponseVO();
        BeanUtils.copyProperties(user, userLoginResponseVO);
        return userLoginResponseVO;
    }
    
    /**
     * 发送QQ邮箱验证码
     * @param username
     */
    public void sendCode(String username) {
        Assert.isTrue(Validator.isEmail(username), "请输入正确的邮箱！");
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        String code = randomGenerator.generate();
        MailDTO mailDTO = MailDTO.builder()
                .toEmail(username)
                .subject(CommonConstant.CAPTCHA)
                .content("您的验证码为 " + code + " 有效期为" + RedisConstant.CODE_EXPIRE_TIME + "分钟")
                .build();
        log.info("发送邮件验证码：{}", mailDTO);
        // 验证码存入消息队列
        //        rabbitTemplate.convertAndSend(MqConstant.EMAIL_EXCHANGE, MqConstant.EMAIL_SIMPLE_KEY, mailDTO);
        CompletableFuture.runAsync(() -> emailService.sendSimpleMail(mailDTO), threadPoolTaskExecutor);
        // 验证码存入redis
        redisService.setObject(RedisConstant.CODE_KEY + username, code, RedisConstant.CODE_EXPIRE_TIME, TimeUnit.MINUTES);
    }
    
    
    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     */
    public void verifyCode(String username, String code) {
        String sysCode = redisService.getObject(RedisConstant.CODE_KEY + username);
        Assert.notBlank(sysCode, "验证码未发送或已过期！");
        Assert.isTrue(sysCode.equals(code), "验证码错误，请重新输入！");
    }
    
    
    /**
     * 用户邮箱注册
     * @param userRegistrationReq
     */
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegistrationReq userRegistrationReq) {
        this.verifyCode(userRegistrationReq.getEmail(), userRegistrationReq.getCode());
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getEmail)
                .eq(User::getEmail, userRegistrationReq.getEmail()));
        Assert.isNull(user, "邮箱已注册！");
        SiteConfig siteConfig = redisService.getObject(RedisConstant.SITE_SETTING);
        // 添加用户
        User newUser = User.builder()
                .email(userRegistrationReq.getEmail())
                .passWord(SecurityUtils.sha256Encrypt(userRegistrationReq.getPassword()))
                .loginType(LoginTypeEnum.EMAIL.getLoginType())
                .isDisable(CommonConstant.FALSE)
                .build();
        userMapper.insert(newUser);
        // 绑定用户角色
        UserRole userRole = UserRole.builder()
                .userId(String.valueOf(newUser.getId()))
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String QQLogin(CodeReq codeRequest) {
        return socialLoginStrategyContext.executeLoginStrategy(codeRequest, LoginTypeEnum.QQ);
    }
    
}
