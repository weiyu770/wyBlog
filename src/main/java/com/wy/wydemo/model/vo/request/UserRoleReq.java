package com.wy.wydemo.model.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: 用户角色Request
 * @class: UserRoleReq
 * @author: yu_wei
 * @create: 2024/11/04 21:04
 */
@Data
@ApiModel(description = "用户角色Request")
public class UserRoleReq {
    
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id", required = true)
    private Long id;
    
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称", required = true)
    private String nickname;
    
    /**
     * 角色id
     */
    @NotEmpty(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id", required = true)
    private List<String> roleIdList;
}