package com.wy.wydemo.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.constant.CommonConstant;
import com.wy.wydemo.constant.RedisConstant;
import com.wy.wydemo.exception.BusinessException;
import com.wy.wydemo.mapper.MenuMapper;
import com.wy.wydemo.mapper.UserMapper;
import com.wy.wydemo.mapper.UserRoleMapper;
import com.wy.wydemo.model.entity.User;
import com.wy.wydemo.model.entity.UserRole;
import com.wy.wydemo.model.enums.RoleEnum;
import com.wy.wydemo.model.enums.StatusCodeEnum;
import com.wy.wydemo.model.vo.query.OnlineUserQuery;
import com.wy.wydemo.model.vo.query.UserQuery;
import com.wy.wydemo.model.vo.request.EmailReq;
import com.wy.wydemo.model.vo.request.MetaReq;
import com.wy.wydemo.model.vo.request.UserRoleReq;
import com.wy.wydemo.model.vo.response.*;
import com.wy.wydemo.model.vo.vo.LoginUserVO;
import com.wy.wydemo.service.UserService;
import com.wy.wydemo.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @description:
 * @class: UserServiceImpl
 * @author: yu_wei
 * @create: 2024/10/26 17:37
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    


    @Resource
    private UserMapper userMapper;
    
    @Autowired
    private MenuMapper menuMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    
    @Autowired
    private RedisService redisService;
    
    
    /**
     * 用户注册
     *
     * @param userName   用户账户
     * @param passWord  用户密码
     * @return
     */
    @Override
    public long userRegister(String userName, String passWord) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userName, passWord)) {
            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR, "参数为空");
        }
        if (userName.length() < 4) {
            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR, "用户账号过短");
        }
        if (passWord.length() < 4 ) {
            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR, "用户密码过短 密码不能少于4位");
        }
        // 2. 查询账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR, "用户账号已存在");
        }

        //        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //        lambdaQueryWrapper.eq(User::getUserName, userName);
        //        Long userCount = userMapper.selectCount(queryWrapper);
        //        if (count > 0) {
        //            throw new BusinessException(StatusCodeEnum.PARAMS_ERROR, "用户账号已存在");
        //        }


        //TODO 上锁
        // 2. 加密
        String encryptPassword = SecurityUtils.sha256Encrypt(passWord);

        // 3. 插入数据
        // 新增用户信息
        User newUser = User.builder()
                .userName(userName)
                .passWord(encryptPassword)
                .loginType(5)//默认手动注册
                .build();
        boolean saveResult = this.save(newUser);
        if (!saveResult) {
            throw new BusinessException(StatusCodeEnum.SYSTEM_ERROR, "注册失败，数据库错误");
        }
        // 绑定用户角色
        UserRole userRole = UserRole.builder()
                .userId(String.valueOf(newUser.getId()))
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
        //注册成功返回用户ID
        return newUser.getId();
    }
    
    
    /**
     * 获取脱敏的已登录用户信息
     *
     * @param user 用户实体对象
     * @return 登录用户的视图对象（脱敏后的用户信息）
     */
    @Override
    @Transactional(readOnly = true)
    public LoginUserVO getLoginUserVO(User user) {
        // 创建并填充 LoginUserVO 实例，复制用户信息（脱敏）
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }




    
    /**
     * 后台登录用户信息
     *
     * @return 登录用户信息
     */
    @Override
    public UserBackInfoResp getUserBackInfo() {
        String userId = String.valueOf(StpUtil.getLoginIdAsLong());
        // 查询用户信息
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getAvatar).eq(User::getId, userId));
        // 查询用户角色
        List<String> roleIdList = StpUtil.getRoleList();
        // 用户权限列表
        List<String> permissionList = StpUtil.getPermissionList().stream()
                .filter(string -> !string.isEmpty())
                .distinct()
                .collect(Collectors.toList());
        return UserBackInfoResp.builder()
                .id(userId)
                .avatar(user != null ? user.getAvatar() : "")
                .roleList(roleIdList)
                .permissionList(permissionList)
                .build();
    }
    
    /**
     * 查看登录用户菜单
     *
     * @return 登录用户菜单
     */
    @Override
    public List<RouterResp> getUserMenu() {
        // 查询用户菜单
        List<UserMenuResp> userMenuRespList = menuMapper.selectMenuByUserId(StpUtil.getLoginIdAsLong());
        // 递归生成路由,parentId为0
        return recurRoutes(CommonConstant.PARENT_ID, userMenuRespList);
    
    }
    
    /**
     * 查看后台用户列表
     *
     * @param userQuery 用户查询条件
     * @return {@link UserBackResp} 用户后台列表
     */
    @Override
    public PageResult<UserBackResp> listUserBackVO(UserQuery userQuery) {
        // 查询后台用户数量
        Long count = userMapper.selectUserCount(userQuery);
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询后台用户列表
        List<UserBackResp> userBackRespList = userMapper.selectUserList(userQuery);
        return new PageResult<>(userBackRespList, count);
    }
    
    public void updateUser(UserRoleReq user) {
        // 更新用户信息
        User newUser = User.builder()
                .id(Long.valueOf(user.getId()))
                .nickName(user.getNickname())
                .build();
        baseMapper.updateById(newUser);
        // 删除用户角色
        userRoleMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
        // 重新添加用户角色
        userRoleMapper.insertUserRole(Long.valueOf(user.getId()), user.getRoleIdList());
        // 删除Redis缓存中的角色
        SaSession sessionByLoginId = StpUtil.getSessionByLoginId(user.getId(), false);
        Optional.ofNullable(sessionByLoginId).ifPresent(saSession -> saSession.delete("Role_List"));
    }
    
    
    public PageResult<OnlineUserResp> listOnlineUser(OnlineUserQuery onlineUserQuery) {
        // 查询所有会话token
        List<String> tokenList = StpUtil.searchTokenSessionId("", 0, -1, false);
        List<OnlineUserResp> onlineUserRespList = tokenList.stream()
                .map(token -> {
                    // 获取tokenSession
                    SaSession sessionBySessionId = StpUtil.getSessionBySessionId(token);
                    return (OnlineUserResp) sessionBySessionId.get(CommonConstant.ONLINE_USER);
                })
                .filter(onlineUserResp -> StringUtils.isEmpty(onlineUserQuery.getKeyword()) || onlineUserResp.getNickname().contains(onlineUserQuery.getKeyword()))
                .sorted(Comparator.comparing(OnlineUserResp::getLoginTime).reversed())
                .collect(Collectors.toList());
        // 计算在线人数并记录日志
        int onlineUserCount = onlineUserRespList.size();
        log.info("当前在线用户数：{}", onlineUserCount);
        
        // 执行分页
        int fromIndex = onlineUserQuery.getCurrent();
        int size = onlineUserQuery.getSize();
        int toIndex = onlineUserRespList.size() - fromIndex > size ? fromIndex + size : onlineUserRespList.size();
        List<OnlineUserResp> userOnlineList = onlineUserRespList.subList(fromIndex, toIndex);
        return new PageResult<>(userOnlineList, (long) onlineUserRespList.size());
    }
    
    /**
     * 下线用户
     * @param token 用户的 token
     */
    public void kickOutUser(String token) {
        // 记录下线用户的 token 日志
        log.info("开始下线用户");
        
        // 执行下线操作
        StpUtil.logoutByTokenValue(token);
        
        // 下线完成日志
        log.info("用户已下线 token: {}", token);
    }
    
    /**
     * 获取用户信息
     * @return
     */
    @Override
    public UserInfoResp getUserInfo() {
        
        long userId = StpUtil.getLoginIdAsLong();
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getNickName, User::getAvatar, User::getUserName, User::getWebSite,
                        User::getIntro, User::getEmail, User::getLoginType)
                .eq(User::getId, userId));
        Set<Object> articleLikeSet = redisService.getSet(RedisConstant.USER_ARTICLE_LIKE + userId);
        Set<Object> commentLikeSet = redisService.getSet(RedisConstant.USER_COMMENT_LIKE + userId);
        Set<Object> talkLikeSet = redisService.getSet(RedisConstant.USER_TALK_LIKE + userId);
        return UserInfoResp
                .builder()
                .id(userId)
                .avatar(user.getAvatar())
                .nickname(user.getNickName())
                .username(user.getUserName())
                .email(user.getEmail())
                .webSite(user.getWebSite())
                .intro(user.getIntro())
                .articleLikeSet(articleLikeSet)
                .commentLikeSet(commentLikeSet)
                .talkLikeSet(talkLikeSet)
                .loginType(user.getLoginType())
                .build();
    }
    
    /**
     * 修改用户邮箱
     * @param email
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserEmail(EmailReq email) {
        
        verifyCode(email.getEmail(), email.getCode());
        User newUser = User.builder()
                .id(StpUtil.getLoginIdAsLong())
                .email(email.getEmail())
                .build();
        userMapper.updateById(newUser);
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
     * 递归生成路由列表
     *
     * @param parentId 父级ID
     * @param menuList 菜单列表
     * @return 路由列表
     */
    private List<RouterResp> recurRoutes(Integer parentId, List<UserMenuResp> menuList) {
        List<RouterResp> list = new ArrayList<>();
        Optional.ofNullable(menuList).ifPresent(menus -> menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .forEach(menu -> {
                    RouterResp routeVO = new RouterResp();
                    routeVO.setName(menu.getMenuName());
                    routeVO.setPath(getRouterPath(menu));
                    routeVO.setComponent(getComponent(menu));
                    routeVO.setMeta(MetaReq.builder()
                            .title(menu.getMenuName())
                            .icon(menu.getIcon())
                            .hidden(menu.getIsHidden().equals(CommonConstant.TRUE))
                            .build());
                    if (menu.getMenuType().equals(CommonConstant.TYPE_DIR)) {
                        List<RouterResp> children = recurRoutes(menu.getId(), menuList);
                        if (CollectionUtil.isNotEmpty(children)) {
                            routeVO.setAlwaysShow(true);
                            routeVO.setRedirect("noRedirect");
                        }
                        routeVO.setChildren(children);
                    } else if (isMenuFrame(menu)) {
                        routeVO.setMeta(null);
                        List<RouterResp> childrenList = new ArrayList<>();
                        RouterResp children = new RouterResp();
                        children.setName(menu.getMenuName());
                        children.setPath(menu.getPath());
                        children.setComponent(menu.getComponent());
                        children.setMeta(MetaReq.builder()
                                .title(menu.getMenuName())
                                .icon(menu.getIcon())
                                .hidden(menu.getIsHidden().equals(CommonConstant.TRUE))
                                .build());
                        childrenList.add(children);
                        routeVO.setChildren(childrenList);
                    }
                    list.add(routeVO);
                }));
        return list;
    }
    
    
    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(UserMenuResp menu) {
        String routerPath = menu.getPath();
        // 一级目录
        if (menu.getParentId().equals(CommonConstant.PARENT_ID) && CommonConstant.TYPE_DIR.equals(menu.getMenuType())) {
            routerPath = "/" + menu.getPath();
        }
        // 一级菜单
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }
    
    
    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(UserMenuResp menu) {
        return menu.getParentId().equals(CommonConstant.PARENT_ID) && CommonConstant.TYPE_MENU.equals(menu.getMenuType());
    }
    
    
    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(UserMenuResp menu) {
        String component = CommonConstant.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = CommonConstant.PARENT_VIEW;
        }
        return component;
    }
    
    
    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(UserMenuResp menu) {
        return !menu.getParentId().equals(CommonConstant.PARENT_ID) && CommonConstant.TYPE_DIR.equals(menu.getMenuType());
    }
    
}













