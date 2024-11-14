package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.User;
import com.wy.wydemo.model.vo.query.UserQuery;
import com.wy.wydemo.model.vo.response.UserBackResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:用户数据库操作
 * @class: UserMapper
 * @author: yu_wei
 * @create: 2024/10/26 17:38
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    
    /**
     * 查询用户后台数量
     *
     * @param userQuery 用户查询条件
     * @return 用户数量
     */
    Long selectUserCount(@Param("param") UserQuery userQuery);
    
    
    /**
     * 查询后台用户列表
     *
     * @param userQuery 用户查询条件
     * @return 用户后台列表
     */
    List<UserBackResp> selectUserList(@Param("param") UserQuery userQuery);
}
