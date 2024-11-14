package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 留言实体类
 * @class: Message
 * @author: yu_wei
 * @create: 2024/11/02 14:26
 */
@TableName("t_message")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    
    /**
     * 留言id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 头像
     */
    private String avatar;
    
    /**
     * 留言内容
     */
    private String messageContent;
    
    /**
     * 用户ip
     */
    private String ipAddress;
    
    /**
     * 用户地址
     */
    private String ipSource;
    
    /**
     * 是否通过 (0否 1是)
     */
    private Integer isCheck;
    
    /**
     * 留言时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
}