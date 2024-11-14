package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 说说查询条件
 * @class: TalkQuery
 * @author: yu_wei
 * @create: 2024/11/08 17:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "说说查询条件")
public class TalkQuery extends PageQuery {
    
    /**
     * 状态 (1公开  2私密)
     */
    @ApiModelProperty(value = "状态 (1公开  2私密)")
    private Integer status;
    
}