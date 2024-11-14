package com.wy.wydemo.model.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @description: 路由其他信息 Response
 * @class: MetaReq
 * @author: yu_wei
 * @create: 2024/11/04 18:09
 */
@Data
@Builder
@ApiModel(description = "路由其他信息Response")
public class MetaReq {
    
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String title;
    
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    
    /**
     * 是否隐藏
     */
    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;
    
}