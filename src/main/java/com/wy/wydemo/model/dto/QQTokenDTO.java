package com.wy.wydemo.model.dto;

import lombok.Data;

/**
 * @description:
 * @class: QQTokenDTO
 * @author: yu_wei
 * @create: 2024/11/04 15:44
 */
@Data
public class QQTokenDTO {
    
    /**
     * openid
     */
    private String openid;
    
    /**
     * 客户端id
     */
    private String client_id;
}