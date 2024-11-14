package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 回复数Response
 * @class: ReplyCountResp
 * @author: yu_wei
 * @create: 2024/11/11 08:56
 */
@Data
@ApiModel(description = "回复数Response")
public class ReplyCountResp {
    
    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id")
    private Integer commentId;
    
    /**
     * 回复数
     */
    @ApiModelProperty(value = "回复数")
    private Integer replyCount;
    
}
