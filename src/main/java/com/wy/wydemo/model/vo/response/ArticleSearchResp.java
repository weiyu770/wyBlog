package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 文章搜索Response
 * @class: ArticleSearchResp
 * @author: yu_wei
 * @create: 2024/11/06 10:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "文章搜索Response")
public class ArticleSearchResp {
    
    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Integer id;
    
    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
    
    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容")
    private String articleContent;
    
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;
    
    /**
     * 文章状态
     */
    @ApiModelProperty(value = "文章状态")
    private Integer status;
}