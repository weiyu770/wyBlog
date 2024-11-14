package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @description: 分类查询条件
 * @class: CategoryQuery
 * @author: yu_wei
 * @create: 2024/11/07 17:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Getter
@ApiModel(description = "分类查询条件")
public class CategoryQuery extends PageQuery {
    
    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容")
    private String keyword;
    
}