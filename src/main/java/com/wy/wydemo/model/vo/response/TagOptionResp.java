package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:标签选项Response
 * @class: TagOptionResp文件
 * @author: yu_wei
 * @create: 2024/11/05 10:34
 */
@Data
@ApiModel(description = "标签选项 Response")
public class TagOptionResp {
    
    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id")
    private Integer id;
    
    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名")
    private String tagName;
    
}
