package com.wy.wydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.mapper.FriendMapper;
import com.wy.wydemo.model.entity.Friend;
import com.wy.wydemo.model.vo.query.FriendQuery;
import com.wy.wydemo.model.vo.request.FriendReq;
import com.wy.wydemo.model.vo.response.FriendBackResp;
import com.wy.wydemo.model.vo.response.FriendResp;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.service.FriendService;
import com.wy.wydemo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @class: FriendServiceImpl
 * @author: yu_wei
 * @create: 2024/11/08 10:10
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {
    
    
    @Autowired
    private FriendMapper friendMapper;
    
    @Override
    public List<FriendResp> listFriendVO() {
        // 查询友链列表
        return friendMapper.selectFriendVOList();
    }
    
    @Override
    public PageResult<FriendBackResp> listFriendBackVO(FriendQuery friendQuery) {
        // 查询友链数量
        Long count = friendMapper.selectCount(new LambdaQueryWrapper<Friend>()
                .like(StringUtils.hasText(friendQuery.getKeyword()), Friend::getName, friendQuery.getKeyword())
        );
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询后台友链列表
        List<FriendBackResp> friendBackVOList = friendMapper.selectFriendBackVOList(friendQuery);
        return new PageResult<>(friendBackVOList, count);
    }
    
    
    /**
     * 添加友链
     * @param friend
     */
    @Override
    public void addFriend(FriendReq friend) {
        // 新友链
        Friend newFriend = BeanCopyUtils.copyBean(friend, Friend.class);
        // 添加友链
        baseMapper.insert(newFriend);
    }
    
    /**
     * 修改友链
     * @param friend
     */
    @Override
    public void updateFriend(FriendReq friend) {
        // 新友链
        Friend newFriend = BeanCopyUtils.copyBean(friend, Friend.class);
        // 更新友链
        baseMapper.updateById(newFriend);
    }
    
}
