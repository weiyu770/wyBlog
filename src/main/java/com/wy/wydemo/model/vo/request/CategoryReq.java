package com.wy.wydemo.model.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description: 分类Request
 * @class: CategoryReq
 * @author: yu_wei
 * @create: 2024/11/07 21:22
 */
@Data
@ApiModel(description = "分类Request")
public class CategoryReq {
    
    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Integer id;
    
    /**
     * 分类名
     */
    @NotBlank(message = "分类名不能为空")
    @ApiModelProperty(value = "分类名", required = true)
    private String categoryName;
    
}