package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @class: PhotoBackResp
 * @author: yu_wei
 * @create: 2024/11/08 16:01
 */
@Data
@ApiModel(description = "后台照片Response")
public class PhotoBackResp {
    
    /**
     * 照片id
     */
    @ApiModelProperty(value = "照片id")
    private Integer id;
    
    /**
     * 照片名
     */
    @ApiModelProperty(value = "照片名")
    private String photoName;
    
    /**
     * 照片描述
     */
    @ApiModelProperty(value = "照片描述")
    private String photoDesc;
    
    /**
     * 照片地址
     */
    @ApiModelProperty(value = "照片地址")
    private String photoUrl;
    
}