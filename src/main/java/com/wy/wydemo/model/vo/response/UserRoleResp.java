package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 用户角色Response
 * @class: UserRoleResp
 * @author: yu_wei
 * @create: 2024/11/04 18:29
 */
@Data
@ApiModel(description = "用户角色Response")
public class UserRoleResp {
    
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private String id;
    
    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String roleName;
}