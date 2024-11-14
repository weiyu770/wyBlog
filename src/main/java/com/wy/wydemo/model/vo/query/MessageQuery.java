package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 留言查询条件
 * @class: MessageQuery
 * @author: yu_wei
 * @create: 2024/11/08 15:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "留言查询条件")
public class MessageQuery extends PageQuery {
    
    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容")
    private String keyword;
    
    /**
     * 是否通过 (0否 1是)
     */
    @ApiModelProperty(value = "是否通过 (0否 1是)")
    private Integer isCheck;
    
}