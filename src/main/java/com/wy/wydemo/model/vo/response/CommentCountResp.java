package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 评论数量Response
 * @class: CommentCountResp
 * @author: yu_wei
 * @create: 2024/11/08 17:43
 */
@Data
@ApiModel(description = "评论数量Response")
public class CommentCountResp {
    /**
     * 类型id
     */
    @ApiModelProperty(value = "类型id")
    private Integer id;
    
    /**
     * 评论数量
     */
    @ApiModelProperty(value = "评论数量")
    private Integer commentCount;
}