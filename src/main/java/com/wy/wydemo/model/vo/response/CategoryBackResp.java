package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: 分类后台Response
 * @class: CategoryBackResp
 * @author: yu_wei
 * @create: 2024/11/07 17:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "分类后台Response")
public class CategoryBackResp extends CategoryResp {
    
    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private Integer parentId;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    
    /**
     * 子分类列表
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "子分类列表")
    private List<CategoryBackResp> children;
    
}