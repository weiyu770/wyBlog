package com.wy.wydemo.model.vo.response;

import com.wy.wydemo.model.entity.SiteConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 博客信息Response
 * @class: BlogInfoResp
 * @author: yu_wei
 * @create: 2024/11/07 17:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "博客信息Response")
public class BlogInfoResp {
    
    /**
     * 文章数量
     */
    @ApiModelProperty(value = "文章数量")
    private Long articleCount;
    
    /**
     * 分类数量
     */
    @ApiModelProperty(value = "分类数量")
    private Long categoryCount;
    
    /**
     * 标签数量
     */
    @ApiModelProperty(value = "标签数量")
    private Long tagCount;
    
    /**
     * 网站访问量
     */
    @ApiModelProperty(value = "网站访问量")
    private String viewCount;
    
    /**
     * 网站配置
     */
    @ApiModelProperty(value = "网站配置")
    private SiteConfig siteConfig;
    
}