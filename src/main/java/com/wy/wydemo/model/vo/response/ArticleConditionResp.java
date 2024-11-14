package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @description: 文章条件Response
 * @class: ArticleConditionResp
 * @author: yu_wei
 * @create: 2024/11/07 23:22
 */
@Data
@ApiModel(description = "文章条件Response")
public class ArticleConditionResp {
    
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
     * 发表时间
     */
    @ApiModelProperty(value = "发表时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
}
