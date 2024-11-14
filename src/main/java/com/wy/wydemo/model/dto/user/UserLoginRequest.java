package com.wy.wydemo.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户登录请求
 * @class: UserLoginRequest
 * @author: yu_wei
 * @create: 2024/10/26 19:30
 */
@Data
@ApiModel("用户登录请求")
public class UserLoginRequest implements Serializable {
    
    private static final long serialVersionUID = 3191241716373120793L;
    
    @ApiModelProperty(value = "用户账号", example = "root")  // 设置默认账号示例为 "root"
    private String userAccount;
    
    @ApiModelProperty(value = "用户密码", example = "123456")  // 设置默认密码示例为 "123456"
    private String passWord;
}
