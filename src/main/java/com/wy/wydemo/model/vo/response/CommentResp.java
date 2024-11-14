package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @description: 评论Response
 * @class: CommentResp
 * @author: yu_wei
 * @create: 2024/11/11 08:49
 */
@Data
@ApiModel(description = "评论Response")
public class CommentResp {
    
    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id")
    private Integer id;
    
    /**
     * 评论用户id
     */
    @ApiModelProperty(value = "评论用户id")
    private Integer fromUid;
    
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String fromNickname;
    
    /**
     * 个人网站
     */
    @ApiModelProperty(value = "个人网站")
    private String webSite;
    
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String commentContent;
    
    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;
    
    /**
     * 回复量
     */
    @ApiModelProperty(value = "回复量")
    private Integer replyCount;
    
    /**
     * 回复列表
     */
    @ApiModelProperty(value = "回复列表")
    private List<ReplyResp> replyVOList;
    
    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
