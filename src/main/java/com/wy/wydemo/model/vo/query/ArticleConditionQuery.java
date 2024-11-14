package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @class: ArticleConditionQuery
 * @author: yu_wei
 * @create: 2024/11/07 23:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "文章条件")
public class ArticleConditionQuery extends PageQuery {
    
    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id", required = true)
    private Integer categoryId;
    
    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id", required = true)
    private Integer tagId;
    
}