package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: 文章首页Response
 * @class: ArticleHomeResp
 * @author: yu_wei
 * @create: 2024/11/06 11:27
 */
@Data
@ApiModel(description = "文章首页Response")
public class ArticleHomeResp {
    
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
    
    /**
     * 文章概要
     */
    @ApiModelProperty(value = "文章概要")
    private String articleDesc;
    
    /**
     * 文章分类
     */
    @ApiModelProperty(value = "文章分类")
    private CategoryOptionResp category;
    
    /**
     * 文章标签
     */
    @ApiModelProperty(value = "文章标签")
    private List<TagOptionResp> tagVOList;
    
    /**
     * 是否置顶 (0否 1是)
     */
    @ApiModelProperty(value = "是否置顶 (0否 1是)")
    private Integer isTop;
    
    /**
     * 发表时间
     */
    @ApiModelProperty(value = "发表时间")
    private LocalDateTime createTime;
}