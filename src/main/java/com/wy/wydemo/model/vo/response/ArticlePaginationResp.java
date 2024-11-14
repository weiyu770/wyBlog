package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:文章上下篇
 * @class: ArticlePaginationResp
 * @author: yu_wei
 * @create: 2024/11/06 12:22
 */
@Data
@ApiModel(description = "文章上下篇")
public class ArticlePaginationResp {
    
    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Integer id;
    
    /**
     * 文章缩略图
     */
    @ApiModelProperty(value = "文章缩略图")
    private String articleCover;
    
    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
}
