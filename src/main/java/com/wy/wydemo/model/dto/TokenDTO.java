package com.wy.wydemo.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: Token
 * @class: TokenDTO
 * @author: yu_wei
 * @create: 2024/11/04 15:43
 */
@Data
@ApiModel(description = "Token")
public class TokenDTO {
    
    /**
     * 访问令牌
     */
    @ApiModelProperty(value = "访问令牌")
    private String access_token;
    
    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    private String expires_in;
    
    /**
     * 刷新token
     */
    @ApiModelProperty(value = "刷新token")
    private String refresh_token;
}
