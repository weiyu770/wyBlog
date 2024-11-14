package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  用户角色实体类
 * @class: UserRole
 * @author: yu_wei
 * @create: 2024/11/02 14:56
 */
@TableName("t_user_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
     * 用户id
     */
    private String userId;
    
    /**
     * 角色id
     */
    private String roleId;
    
}