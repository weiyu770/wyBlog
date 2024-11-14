package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @class: TagQuery
 * @author: yu_wei
 * @create: 2024/11/08 17:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "标签查询条件")
public class TagQuery extends PageQuery {
    
    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容")
    private String keyword;
    
}