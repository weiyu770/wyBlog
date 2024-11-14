package com.wy.wydemo.model.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description: 留言Request
 * @class: MessageReq
 * @author: yu_wei
 * @create: 2024/11/08 15:47
 */
@Data
@ApiModel(description = "留言Request")
public class MessageReq {
    
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称", required = true)
    private String nickname;
    
    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空")
    @ApiModelProperty(value = "头像", required = true)
    private String avatar;
    
    /**
     * 留言内容
     */
    @NotBlank(message = "留言内容不能为空")
    @ApiModelProperty(value = "留言内容", required = true)
    private String messageContent;
}