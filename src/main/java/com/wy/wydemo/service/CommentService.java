package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Comment;
import com.wy.wydemo.model.vo.query.CommentQuery;
import com.wy.wydemo.model.vo.request.CheckReq;
import com.wy.wydemo.model.vo.request.CommentReq;
import com.wy.wydemo.model.vo.response.*;

import java.util.List;

/**
 * @description:
 * @class: CommentService
 * @author: yu_wei
 * @create: 2024/11/07 23:42
 */
public interface CommentService extends IService<Comment> {
    
    
    /**
     * 查看后台评论列表
     * @param commentQuery
     * @return
     */
    PageResult<CommentBackResp> listCommentBackVO(CommentQuery commentQuery);
    
    
    /**
     * 添加评论
     * @param comment
     */
    void addComment(CommentReq comment);
    
    
    /**
     * 审核评论
     * @param check
     */
    void updateCommentCheck(CheckReq check);
    
    
    /**
     * 查看最新评论
     * @return
     */
    List<RecentCommentResp> listRecentCommentVO();
    
    
    /**
     * 查看评论
     * @param commentQuery
     * @return
     */
    PageResult<CommentResp> listCommentVO(CommentQuery commentQuery);
    
    
    /**
     * 查看回复评论
     * @param commentId
     * @return
     */
    List<ReplyResp> listReply(Integer commentId);
}
