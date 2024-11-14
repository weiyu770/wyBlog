package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:相册Response
 * @class: AlbumResp
 * @author: yu_wei
 * @create: 2024/11/06 16:47
 */
@Data
@ApiModel(description = "相册Response")
public class AlbumResp {
    
    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id")
    private Integer id;
    
    /**
     * 相册名
     */
    @ApiModelProperty(value = "相册名")
    private String albumName;
    
    /**
     * 相册封面
     */
    @ApiModelProperty(value = "相册封面")
    private String albumCover;
    
    /**
     * 相册描述
     */
    @ApiModelProperty(value = "相册描述")
    private String albumDesc;
    
}