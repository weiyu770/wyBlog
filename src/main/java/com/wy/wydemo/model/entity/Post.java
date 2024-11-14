package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户帖子实体类
 * @class: Post
 * author: yu_wei
 * @create: 2024/10/26 16:55
 */
@TableName(value = "post")
@Data
public class Post implements Serializable {
    
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;
    
    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;
    
    /**
     * 标签列表（json 数组）
     */
    @TableField(value = "tags")
    private String tags;
    
    /**
     * 点赞数
     */
    @TableField(value = "thumbNum")
    private Integer thumbNum;
    
    /**
     * 收藏数
     */
    @TableField(value = "favourNum")
    private Integer favourNum;
    
    /**
     * 创建用户 id
     */
    @TableField(value = "userId")
    private Long userId;
    
    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;
    
    /**
     * 是否删除
     */
    @TableField(value = "isDelete")
    @TableLogic
    private Integer isDelete;
    
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
