package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 回复Response
 * @class: ReplyResp
 * @author: yu_wei
 * @create: 2024/11/11 08:55
 */
@Data
@ApiModel(description = "回复Response")
public class ReplyResp {
    
    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id")
    private Integer id;
    
    /**
     * 父级评论id
     */
    @ApiModelProperty(value = "父级评论id")
    private Integer parentId;
    
    /**
     * 评论用户id
     */
    @ApiModelProperty(value = "评论用户id")
    private Integer fromUid;
    
    /**
     * 被评论用户id
     */
    @ApiModelProperty(value = "被评论用户id")
    private Integer toUid;
    
    /**
     * 评论用户昵称
     */
    @ApiModelProperty(value = "评论用户昵称")
    private String fromNickname;
    
    /**
     * 个人网站
     */
    @ApiModelProperty(value = "个人网站")
    private String webSite;
    
    /**
     * 被评论用户昵称
     */
    @ApiModelProperty(value = "被评论用户昵称")
    private String toNickname;
    
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
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}

