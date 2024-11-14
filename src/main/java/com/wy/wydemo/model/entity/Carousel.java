package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:轮播图
 * @class: Carousel
 * @author: yu_wei
 * @create: 2024/11/02 15:17
 */
@TableName("t_carousel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carousel {
    
    /**
     * 轮播图id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 轮播图地址
     */
    private String imgUrl;
    
    /**
     * 是否显示 (0否 1是)
     */
    private Integer status;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
}