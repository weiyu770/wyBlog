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
 * @description:文件实体类
 * @class: BlogFile
 * @author: yu_wei
 * @create: 2024/11/02 14:01
 */
@TableName("t_blog_file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogFile {
    /**
     * 文件id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 文件url
     */
    private String fileUrl;
    
    /**
     * 文件名
     */
    private String fileName;
    
    /**
     * 文件大小
     */
    private Integer fileSize;
    
    /**
     * 文件类型
     */
    private String extendName;
    
    /**
     * 文件路径
     */
    private String filePath;
    
    /**
     * 是否为目录 (0否 1是)
     */
    private Integer isDir;
    
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