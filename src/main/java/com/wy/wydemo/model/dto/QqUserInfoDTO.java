package com.wy.wydemo.model.dto;

import lombok.Data;

/**
 * @description: 获取QQ返回的用户信息 QQ用户信息
 * @class: QqUserInfoDTO
 * @author: yu_wei
 * @create: 2024/11/04 15:46
 */
@Data
public class QqUserInfoDTO {
    
    /**
     * 用户开放id
     */
    private String openId;
    
    /**
     * QQ头像
     */
    private String figureurl_qq_1;
    
    /**
     * 昵称
     */
    private String nickname;
}