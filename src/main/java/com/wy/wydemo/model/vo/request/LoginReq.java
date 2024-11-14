package com.wy.wydemo.model.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @description: 用户登录请求
 * @class: LoginReq
 * @author: yu_wei
 * @create: 2024/11/02 17:13
 */
@Data
@ApiModel(description = "用户登录请求")
public class LoginReq {
    
    private static final long serialVersionUID = 3191241716373120793L;
    
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", example = "root", required = true)
    private String userName;
    
    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码不能少于6位")
    @ApiModelProperty(value = "用户密码", example = "123456", required = true)
    private String passWord;
}
