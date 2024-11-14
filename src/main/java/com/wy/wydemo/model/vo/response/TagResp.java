package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 标签Response
 * @class: TagResp
 * @author: yu_wei
 * @create: 2024/11/08 16:43
 */
@Data
@ApiModel(description = "标签Response")
public class TagResp {
    
    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id")
    private Integer id;
    
    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名")
    private String tagName;
    
    /**
     * 文章数量
     */
    @ApiModelProperty(value = "文章数量")
    private Integer articleCount;
}