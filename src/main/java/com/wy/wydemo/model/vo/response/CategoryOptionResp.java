package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 分类选项Response
 * @class: CategoryOptionResp
 * @author: yu_wei
 * @create: 2024/11/06 11:28
 */
@Data
@ApiModel(description = "分类选项Response")
public class CategoryOptionResp {
    
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
    
}