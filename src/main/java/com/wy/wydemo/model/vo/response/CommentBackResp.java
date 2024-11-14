package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 评论后台Response
 * @class: CommentBackResp
 * @author: yu_wei
 * @create: 2024/11/07 23:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "评论后台Response")
public class CommentBackResp {
    
    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id")
    private String id;
    
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    /**
     * 评论用户昵称
     */
    @ApiModelProperty(value = "评论用户昵称")
    private String fromNickname;
    
    /**
     * 被回复用户昵称
     */
    @ApiModelProperty(value = "被回复用户昵称")
    private String toNickname;
    
    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
    
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String commentContent;
    
    /**
     * 评论类型
     */
    @ApiModelProperty(value = "评论类型")
    private Integer commentType;
    
    /**
     * 是否通过 (0否 1是)
     */
    @ApiModelProperty(value = "是否通过 (0否 1是)")
    private Integer isCheck;
    
    /**
     * 发表时间
     */
    @ApiModelProperty(value = "发表时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    
}