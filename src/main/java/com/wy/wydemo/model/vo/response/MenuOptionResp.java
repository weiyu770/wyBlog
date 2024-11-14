package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 菜单选项树Response
 * @class: MenuOptionResp
 * @author: yu_wei
 * @create: 2024/11/08 15:26
 */
@Data
@ApiModel(description = "菜单选项树Response")
public class MenuOptionResp {
    
    /**
     * id
     */
    @ApiModelProperty(value = "菜单id")
    private Integer value;
    
    /**
     * 父菜单id
     */
    @JsonIgnore
    @ApiModelProperty(value = "父菜单id")
    private Integer parentId;
    
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String label;
    
    /**
     * 子菜单树
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "子菜单树")
    private List<MenuOptionResp> children;
}