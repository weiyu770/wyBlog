package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 轮播图Response
 * @class: CarouselResp
 * @author: yu_wei
 * @create: 2024/11/07 20:06
 */
@Data
@ApiModel(value = "轮播图Response")
public class CarouselResp {
    
    /**
     * 轮播图id
     */
    @ApiModelProperty(value = "轮播图id")
    private Integer id;
    
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String imgUrl;
    
}