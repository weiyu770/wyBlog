package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 最新评论Response
 * @class: RecentCommentResp
 * @author: yu_wei
 * @create: 2024/11/11 08:47
 */
@Data
@ApiModel(description = "最新评论Response")
public class RecentCommentResp {
    
    /**
     * 评论id
     */
    @ApiModelProperty(value = "评论id")
    private Integer id;
    
    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户昵称")
    private String avatar;
    
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String commentContent;
    
    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}