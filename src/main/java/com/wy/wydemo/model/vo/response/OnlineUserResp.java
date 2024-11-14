package com.wy.wydemo.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description: 在线用户Response
 * @class: OnlineUserResp
 * @author: yu_wei
 * @create: 2024/11/04 21:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "在线用户Response")
public class OnlineUserResp {
    
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long id;
    
    /**
     * 在线token
     */
    @ApiModelProperty(value = "在线token")
    private String token;
    
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String avatar;
    
    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    
    /**
     * ip地址
     */
    @ApiModelProperty(value = "ip地址")
    private String ipAddress;
    
    /**
     * ip来源
     */
    @ApiModelProperty(value = "ip来源")
    private String ipSource;
    
    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统")
    private String os;
    
    /**
     * 浏览器
     */
    @ApiModelProperty(value = "浏览器")
    private String browser;
    
    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime loginTime;
    
}