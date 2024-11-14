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
 * @description: 说说实体类
 * @class: Talk
 * @author: yu_wei
 * @create: 2024/11/02 14:53
 */
@TableName("t_talk")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Talk {
    
    /**
     * 说说id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 用户id
     */
    private Integer userId;
    
    /**
     * 说说内容
     */
    private String talkContent;
    
    /**
     * 说说图片
     */
    private String images;
    
    /**
     * 是否置顶 (0否 1是)
     */
    private Integer isTop;
    
    /**
     * 状态 (1公开  2私密)
     */
    private Integer status;
    
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