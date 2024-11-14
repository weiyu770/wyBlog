package com.wy.wydemo.model.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description:
 * @class: RoleResp
 * @author: yu_wei
 * @create: 2024/11/08 16:25
 */
@Data
@ApiModel(description = "角色Response")
public class RoleResp {
    
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
    
    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述")
    private String roleDesc;
    
    /**
     * 是否禁用 (0否 1是)
     */
    @ApiModelProperty(value = "是否禁用 (0否 1是)")
    private Integer isDisable;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
}