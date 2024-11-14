package com.wy.wydemo.model.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 角色状态Request
 * @class: RoleStatusReq
 * @author: yu_wei
 * @create: 2024/11/08 16:36
 */
@Data
@ApiModel(description = "角色状态Request")
public class RoleStatusReq {
    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id", required = true)
    private String id;
    
    /**
     * 是否禁用 (0否 1是)
     */
    @NotNull(message = "角色状态不能为空")
    @ApiModelProperty(value = "是否禁用 (0否 1是)", required = true)
    private Integer isDisable;
}