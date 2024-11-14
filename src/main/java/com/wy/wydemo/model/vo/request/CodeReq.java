package com.wy.wydemo.model.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @class: CodeReq uest
 * @author: yu_wei
 * @create: 2024/11/04 15:13
 *
 */
//这是一个请求数据的封装类，用于接收前端传递的验证码信息
// 包含了一个 code 属性，这是用户通过第三方登录（如 QQ 登录）获取的验证码。
@Data
@ApiModel(description = "Code信息")
public class CodeReq {
    
    /**
     * code
     */
    @NotBlank(message = "code不能为空")
    @ApiModelProperty(value = "code", required = true)
    private String code;
}
