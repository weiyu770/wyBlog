package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description:相册
 * @class: Album
 * @author: yu_wei
 * @create: 2024/11/06 16:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_album")
public class Album {
    
    /**
     * 相册id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 相册名
     */
    private String albumName;
    
    /**
     * 相册封面
     */
    private String albumCover;
    
    /**
     * 相册描述
     */
    private String albumDesc;
    
    /**
     * 状态 (1公开 2私密)
     */
    private Integer status;
    
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