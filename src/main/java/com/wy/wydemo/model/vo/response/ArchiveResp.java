package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description: 文章归档Response
 * @class: ArchiveResp
 * @author: yu_wei
 * @create: 2024/11/06 14:06
 */
@Data
@ApiModel(description = "文章归档Response")
public class ArchiveResp {
    
    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Integer id;
    
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String articleTitle;
    
    /**
     * 文章缩略图
     */
    @ApiModelProperty(value = "文章缩略图")
    private String articleCover;
    
    /**
     * 发表时间
     */
    @ApiModelProperty(value = "发表时间")
    private LocalDateTime createTime;
}
