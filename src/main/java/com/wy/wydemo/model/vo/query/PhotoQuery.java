package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @class: PhotoQuery
 * @author: yu_wei
 * @create: 2024/11/08 16:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "照片查询条件")
public class PhotoQuery extends PageQuery {
    
    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容")
    private String keyword;
    
    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id")
    private Integer albumId;
    
}