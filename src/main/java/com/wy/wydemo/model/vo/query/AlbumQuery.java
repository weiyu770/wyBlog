package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 相册查询条件
 * @class: AlbumQuery
 * @author: yu_wei
 * @create: 2024/11/06 16:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "相册查询条件")
public class AlbumQuery extends PageQuery {
    
    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容")
    private String keyword;
    
}