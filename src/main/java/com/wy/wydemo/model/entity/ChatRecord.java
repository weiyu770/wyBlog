package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 聊天记录
 * @class: ChatRecord
 * @author: yu_wei
 * @create: 2024/11/02 14:05
 */
@TableName("t_chat_record")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecord {
    
    /**
     * 聊天记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 用户id
     */
    private Integer userId;
    
    /**
     * 用户昵称
     */
    private String nickname;
    
    /**
     * 用户头像
     */
    private String avatar;
    
    /**
     * 聊天内容
     */
    private String content;
    
    /**
     * 用户登录ip
     */
    private String ipAddress;
    
    /**
     * ip来源
     */
    private String ipSource;
    
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    
}