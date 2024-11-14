package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Role;
import com.wy.wydemo.model.vo.query.RoleQuery;
import com.wy.wydemo.model.vo.request.RoleReq;
import com.wy.wydemo.model.vo.request.RoleResp;
import com.wy.wydemo.model.vo.request.RoleStatusReq;
import com.wy.wydemo.model.vo.response.PageResult;

import java.util.List;

/**
 * @description:
 * @class: RoleService
 * @author: yu_wei
 * @create: 2024/11/08 16:23
 */
public interface RoleService extends IService<Role> {
    
    /**
     * 查看后台角色列表
     * @param roleQuery
     * @return
     */
    PageResult<RoleResp> listRoleVO(RoleQuery roleQuery);
    
    
    /**
     * 添加角色
     * @param role
     */
    void addRole(RoleReq role);
    
    
    /**
     * 删除角色
     * @param roleIdList
     */
    void deleteRole(List<String> roleIdList);
    
    /**
     * 修改角色
     * @param role
     */
    void updateRole(RoleReq role);
    
    
    /**
     * 修改角色状态
     * @param roleStatus
     */
    void updateRoleStatus(RoleStatusReq roleStatus);
    
    /**
     * 查看角色的菜单权限
     * @param roleId
     * @return
     */
    List<Integer> listRoleMenuTree(String roleId);
}
