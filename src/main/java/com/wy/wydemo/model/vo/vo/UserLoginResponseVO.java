package com.wy.wydemo.model.vo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户前端视图
 * @class: UserLoginResponseVO
 * @create: 2024/10/26 19:28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登录响应视图")
public class UserLoginResponseVO {
    
    /**
     * 用户昵称/用户名字
     */
    @ApiModelProperty(value = "用户昵称/用户名字")
    private String nickName;
    
    /**
     * 用户名/用户账号
     */
    @ApiModelProperty(value = "用户名/用户账号")
    private String userName;
    
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像URL")
    private String avatar;
    
    /**
     * 个人网站
     */
    @ApiModelProperty(value = "个人网站URL")
    private String webSite;
    
    /*
     * 个人介绍
     */
    @ApiModelProperty(value = "个人介绍")
    private String intro;
    
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱地址")
    private String email;
    
    /**
     * 登录方式
     */
    @ApiModelProperty(value = "登录方式", example = "1", notes = "1-邮箱登录, 2-QQ, 3-Gitee, 4-Github")
    private Integer loginType;
}
