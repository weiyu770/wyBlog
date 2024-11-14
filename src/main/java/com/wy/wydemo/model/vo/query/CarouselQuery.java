package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 轮播图查询条件
 * @class: CarouselQuery
 * @author: yu_wei
 * @create: 2024/11/07 20:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "轮播图查询条件")
public class CarouselQuery extends PageQuery {
    
    /**
     * 是否显示 (0否 1是)
     */
    @ApiModelProperty(value = "是否显示 (0否 1是)")
    private Integer status;
}