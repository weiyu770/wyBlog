package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.model.entity.Friend;
import com.wy.wydemo.model.vo.query.FriendQuery;
import com.wy.wydemo.model.vo.request.FriendReq;
import com.wy.wydemo.model.vo.response.FriendBackResp;
import com.wy.wydemo.model.vo.response.FriendResp;
import com.wy.wydemo.model.vo.response.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @class: FriendService
 * @author: yu_wei
 * @create: 2024/11/08 10:09
 */
public interface FriendService extends IService<Friend> {
    /**
     * 查看友链列表
     * @return
     */
    List<FriendResp> listFriendVO();
    
    /**
     * 查看后台友链列表
     * @param friendQuery
     * @return
     */
    PageResult<FriendBackResp> listFriendBackVO(FriendQuery friendQuery);
    
    /**
     * 添加友链
     * @param friend
     */
    void addFriend(FriendReq friend);
    
    /**
     * 修改友链
     * @param friend
     */
    void updateFriend(FriendReq friend);
}
