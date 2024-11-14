package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @description: 标签后台Response
 * @class: TagBackResp
 * @author: yu_wei
 * @create: 2024/11/08 16:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "标签后台Response")
public class TagBackResp extends TagResp {
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    
}