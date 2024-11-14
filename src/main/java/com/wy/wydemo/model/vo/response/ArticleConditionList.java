package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @class: ArticleConditionList
 * @author: yu_wei
 * @create: 2024/11/07 23:22
 */
@Data
@Builder
@ApiModel(description = "文章条件列表VO")
public class ArticleConditionList {
    
    /**
     * 文章列表
     */
    @ApiModelProperty(value = "文章列表")
    private List<ArticleConditionResp> articleConditionVOList;
    
    /**
     * 条件名
     */
    @ApiModelProperty(value = "条件名")
    private String name;
}