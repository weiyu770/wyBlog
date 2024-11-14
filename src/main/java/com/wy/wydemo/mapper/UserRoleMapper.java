package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @class: UserRoleMapper
 * @author: yu_wei
 * @create: 2024/11/04 13:21
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    
    /**
     * 根据用户id查询角色id
     *
     * @param userId 用户id
     * @return 角色id
     */
    List<String> selectRoleIdByUserId(@Param("userId") Integer userId);
    
    /**
     * 添加用户角色
     *
     * @param userId     用户id
     * @param roleIdList 角色id集合
     */
    void insertUserRole(@Param("userId") Long userId, @Param("roleIdList") List<String> roleIdList);
}
