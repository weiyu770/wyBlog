package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @description: 文章浏览量排行Response
 * @class: ArticleRankResp
 * @author: yu_wei
 * @create: 2024/11/07 17:25
 */
@Data
@Builder
@ApiModel(description = "文章浏览量排行Response")
public class ArticleRankResp {
    
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String articleTitle;
    
    /**
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量")
    private Integer viewCount;
    
}
