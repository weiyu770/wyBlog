package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 留言Response
 * @class: MessageResp
 * @author: yu_wei
 * @create: 2024/11/08 15:41
 */
@Data
@ApiModel(description = "留言Response")
public class MessageResp {
    
    /**
     * 留言id
     */
    @ApiModelProperty(value = "留言id")
    private Integer id;
    
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    /**
     * 留言内容
     */
    @ApiModelProperty(value = "留言内容")
    private String messageContent;
}