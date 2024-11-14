package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 标签实体类
 * @class: Tag
 * @author: yu_wei
 * @create: 2024/11/02 14:32
 */
@TableName("t_tag")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    
    /**
     * 标签id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 标签名
     */
    private String tagName;
    
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
