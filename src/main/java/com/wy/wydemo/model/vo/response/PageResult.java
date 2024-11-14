package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @class: PageResult
 * @author: yu_wei
 * @create: 2024/11/04 18:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "分页返回类")
public class PageResult<T> {
    
    /**
     * 分页结果
     */
    @ApiModelProperty(value = "分页结果")
    private List<T> recordList;
    
    /**
     * 总数
     */
    @ApiModelProperty(value = "总数", dataType = "long")
    private Long count;
    
}