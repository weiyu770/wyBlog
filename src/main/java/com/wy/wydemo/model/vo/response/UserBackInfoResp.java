package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @description: 后台登录用户信息Response
 * @class: UserBackInfoResp
 * @author: yu_wei
 * @create: 2024/11/04 16:39
 */
@Data
@Builder
@ApiModel(description = "后台登录用户信息Response")
public class UserBackInfoResp {
    
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String id;
    
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    /**
     * 角色
     */
    @ApiModelProperty(value = "角色")
    private List<String> roleList;
    
    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private List<String> permissionList;
    
}