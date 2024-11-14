package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:  评论查询条件
 * @class: CommentQuery
 * @author: yu_wei
 * @create: 2024/11/07 23:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "评论查询条件")
public class CommentQuery extends PageQuery {
    
    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容")
    private String keyword;
    
    /**
     * 类型id
     */
    @ApiModelProperty(value = "类型id")
    private Integer typeId;
    
    /**
     * 评论主题类型
     */
    @ApiModelProperty(value = "评论主题类型")
    private Integer commentType;
    
    /**
     * 是否通过 (0否 1是)
     */
    @ApiModelProperty(value = "是否通过 (0否 1是)")
    private Integer isCheck;
    
}