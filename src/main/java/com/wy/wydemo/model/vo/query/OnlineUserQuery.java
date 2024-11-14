package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 在线用户查询条件
 * @class: OnlineUserQuery
 * @author: yu_wei
 * @create: 2024/11/04 21:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "在线用户查询条件")
public class OnlineUserQuery extends PageQuery {
    
    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容")
    private String keyword;
    
}