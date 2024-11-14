package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 角色查询条件
 * @class: RoleQuery
 * @author: yu_wei
 * @create: 2024/11/08 16:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "角色查询条件")
public class RoleQuery extends PageQuery {
    
    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容")
    private String keyword;
    
    /**
     * 是否禁用 (0否 1是)
     */
    @ApiModelProperty(value = "是否禁用 (0否 1是)")
    private Integer isDisable;
    
}