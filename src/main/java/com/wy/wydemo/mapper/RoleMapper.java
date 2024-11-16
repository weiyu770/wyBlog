package com.wy.wydemo.mapper;

import co.elastic.clients.elasticsearch.xpack.usage.Base;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Role;
import com.wy.wydemo.model.vo.query.RoleQuery;
import com.wy.wydemo.model.vo.request.RoleResp;
import com.wy.wydemo.model.vo.response.UserRoleResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: RoleMapper
 * @author: yu_wei
 * @create: 2024/11/08 16:24
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    
    /**
     * 查询后台角色数量
     *
     * @param roleQuery 角色查询条件
     * @return 后台角色数量
     */
    Long selectBackRoleCount(@Param("param") RoleQuery roleQuery);
    
    /**
     * 查询后台角色列表
     *
     * @param roleQuery 角色查询条件
     * @return 后台角色列表
     */
    List<RoleResp> selectBackRoleList(@Param("param") RoleQuery roleQuery);
        /**
     * 查询用户角色选项
     *
     * @return 用户角色选项
     */
    List<UserRoleResp> selectUserRoleList();
}
