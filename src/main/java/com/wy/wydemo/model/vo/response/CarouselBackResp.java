package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description:
 * @class: CarouselBackResp
 * @author: yu_wei
 * @create: 2024/11/07 20:10
 */
@Data
@ApiModel(value = "轮播图后台Response")
public class CarouselBackResp {
    
    /**
     * 轮播图id
     */
    @ApiModelProperty(value = "轮播图id")
    private Integer id;
    
    /**
     * 轮播图地址
     */
    @ApiModelProperty(value = "轮播图地址")
    private String imgUrl;
    
    /**
     * 是否显示 (0否 1是)
     */
    @ApiModelProperty(value = "是否显示 (0否 1是)")
    private Integer status;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
}