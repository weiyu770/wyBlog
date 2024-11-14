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
 * @description: 照片实体类
 * @class: Photo
 * @author: yu_wei
 * @create: 2024/11/02 14:27
 */
@TableName("t_photo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    
    /**
     * 照片id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 相册id
     */
    private Integer albumId;
    
    /**
     * 照片名
     */
    private String photoName;
    
    /**
     * 照片描述
     */
    private String photoDesc;
    
    /**
     * 照片链接
     */
    private String photoUrl;
    
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