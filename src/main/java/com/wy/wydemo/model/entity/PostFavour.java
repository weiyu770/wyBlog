package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 帖子收藏实体类
 * @class: PostFavour
 * author: yu_wei
 * @create: 2024/10/26 16:58
 */
@TableName(value = "post_favour")
@Data
public class PostFavour implements Serializable {
    
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 帖子 id
     */
    @TableField(value = "postId")
    private Long postId;
    
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
    
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
