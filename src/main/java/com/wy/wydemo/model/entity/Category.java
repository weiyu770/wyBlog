package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * @description: 分类实体类
 * @class: Category
 * @author: yu_wei
 * @create: 2024/11/02 14:04
 */
@TableName("t_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    
    /**
     * 分类id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 分类名
     */
    private String categoryName;
    
    /**
     * 父级ID
     */
    private Integer parentId;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
   
}
