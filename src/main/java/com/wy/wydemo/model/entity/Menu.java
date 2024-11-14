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
 * @description:
 * @class: Menu
 * @author: yu_wei
 * @create: 2024/11/02 14:21
 */
@TableName("t_menu")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    
    /**
     * 菜单id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 父菜单id
     */
    private Integer parentId;
    
    /**
     * 类型（M目录 C菜单 B按钮）
     */
    private String menuType;
    
    /**
     * 名称
     */
    private String menuName;
    
    /**
     * 路由地址
     */
    private String path;
    
    /**
     * 菜单图标
     */
    private String icon;
    
    /**
     * 菜单组件
     */
    private String component;
    
    /**
     * 权限标识
     */
    private String perms;
    
    /**
     * 是否隐藏 (0否 1是)
     */
    private Integer isHidden;
    
    /**
     * 是否禁用 (0否 1是)
     */
    private Integer isDisable;
    
    /**
     * 排序
     */
    private Integer orderNum;
    
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