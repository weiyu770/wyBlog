package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 角色菜单实体类
 * @class: RoleMenu
 * @author: yu_wei
 * @create: 2024/11/02 14:29
 */
//TODO: 不太明白
@TableName("t_role_menu")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu {
    
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 角色id
     */
    private String roleId;
    
    /**
     * 菜单id
     */
    private Integer menuId;
    
}