package com.wy.wydemo.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 用户注册请求体
 * @class: UserRegisterRequest
 * @author: yu_wei
 * @create: 2024/10/26 17:35
 */
@Data
@ApiModel(description = "用户注册请求")
public class UserRegisterRequest implements Serializable {
    
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
    @Size(min = 4, message = "密码不能少于4位")
    @ApiModelProperty(value = "用户密码", example = "123456", required = true)
    private String passWord;
}
