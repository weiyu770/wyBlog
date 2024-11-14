package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 友链Response
 * @class: FriendResp
 * @author: yu_wei
 * @create: 2024/11/08 10:16
 */
@Data
@ApiModel(description = "友链Response")
public class FriendResp {
    
    /**
     * 友链id
     */
    @ApiModelProperty(value = "友链id")
    private Integer id;
    
    /**
     * 友链颜色
     */
    @ApiModelProperty(value = "友链颜色")
    private String color;
    
    /**
     * 友链名称
     */
    @ApiModelProperty(value = "友链名称")
    private String name;
    
    /**
     * 友链头像
     */
    @ApiModelProperty(value = "友链头像")
    private String avatar;
    
    /**
     * 友链地址
     */
    @ApiModelProperty(value = "友链地址")
    private String url;
    
    /**
     * 友链介绍
     */
    @ApiModelProperty(value = "友链介绍")
    private String introduction;
    
}