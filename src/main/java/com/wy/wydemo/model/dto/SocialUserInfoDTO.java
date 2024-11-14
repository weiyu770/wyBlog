package com.wy.wydemo.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @description: 第三方账号信息DTO
 * @class: SocialUserInfoDTO
 * @author: yu_wei
 * @create: 2024/11/04 15:40
 */
@Data
@Builder
@ApiModel(description = "第三方账号信息DTO")
public class SocialUserInfoDTO {
    
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
}