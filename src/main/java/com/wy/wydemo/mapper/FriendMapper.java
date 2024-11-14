package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Friend;
import com.wy.wydemo.model.vo.query.FriendQuery;
import com.wy.wydemo.model.vo.response.FriendBackResp;
import com.wy.wydemo.model.vo.response.FriendResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: FriendMapper
 * @author: yu_wei
 * @create: 2024/11/08 10:11
 */
@Mapper
public interface FriendMapper extends BaseMapper<Friend> {
    
    
    
    /**
     * 查看友链列表
     *
     * @return 友链列表
     */
    List<FriendResp> selectFriendVOList();
    
    
    /**
     * 查看友链后台列表
     *
     * @param friendQuery 友链查询条件
     * @return 友链后台列表
     */
    List<FriendBackResp> selectFriendBackVOList(@Param("param") FriendQuery friendQuery);
}
