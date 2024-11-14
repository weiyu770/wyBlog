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
 * @description: 角色实体类
 * @class: Role
 * @author: yu_wei
 * @create: 2024/11/02 14:28
 */
@TableName("t_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    /**
     * 角色id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    
    /**
     * 角色名
     */
    private String roleName;
    
    /**
     * 角色描述
     */
    private String roleDesc;
    
    /**
     * 是否禁用 (0否 1是)
     */
    private Integer isDisable;
    
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