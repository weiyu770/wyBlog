package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 评论实体类
 * @class: Comment
 * @author: yu_wei
 * @create: 2024/11/02 14:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_comment")
public class Comment {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 类型 (1文章 2友链 3说说)
     */
    @TableField("comment_type")
    private Integer commentType;
    
    /**
     * 类型id
     */
    @TableField("type_id")
    private Integer typeId;
    
    /**
     * 父评论id
     */
    @TableField("parent_id")
    private Integer parentId;
    
    /**
     * 回复评论id
     */
    @TableField("reply_id")
    private Integer replyId;
    
    /**
     * 评论内容
     */
    @TableField("comment_content")
    private String commentContent;
    
    /**
     * 评论用户id
     */
    @TableField("from_uid")
    private Long fromUid;
    
    /**
     * 回复用户id
     */
    @TableField("to_uid")
    private Long toUid;
    
    /**
     * 是否通过 (0否 1是)
     */
    @TableField("is_check")
    private Integer isCheck;
    
    /**
     * 评论时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
