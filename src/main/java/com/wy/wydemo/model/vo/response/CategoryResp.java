package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 分类Response
 * @class: CategoryResp
 * @author: yu_wei
 * @create: 2024/11/07 17:25
 */
@Data
@ApiModel(description = "分类Response")
public class CategoryResp {
    
    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Integer id;
    
    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    private String categoryName;
    
    /**
     * 文章数量
     */
    @ApiModelProperty(value = "文章数量")
    private Integer articleCount;
    
}