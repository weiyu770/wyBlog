package com.wy.wydemo.model.vo.query;

import com.wy.wydemo.constant.PageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 分页查询条件
 * @class: PageQuery
 * @author: yu_wei
 * @create: 2024/11/04 18:30
 */
@Data
@ApiModel(description = "分页查询条件")
public class PageQuery {
    
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true,example = "1")
    private Integer current;
    
    /**
     * 条数
     */
    @ApiModelProperty(value = "条数", required = true,example = "10")
    private Integer size;
    
    public Integer getCurrent() {
        return current == null ? (PageConstant.DEFAULT_CURRENT - 1) * getSize() : (current - 1) * getSize();
    }
    
    public Integer getSize() {
        return size == null ? PageConstant.MY_SIZE : size;
    }
    
}