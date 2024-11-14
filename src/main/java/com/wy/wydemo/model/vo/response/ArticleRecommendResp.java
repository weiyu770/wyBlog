package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: Response
 * @class: ArticleRecommendResp
 * @author: yu_wei
 * @create: 2024/11/06 14:00
 */
@Data
@ApiModel(description = "推荐文章Response")
public class ArticleRecommendResp {
    
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
     * 文章缩略图
     */
    @ApiModelProperty(value = "文章缩略图")
    private String articleCover;
    
    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}