package com.wy.wydemo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 删除请求 因为统一根据ID删除 所以放在了Common中
 * @class: DeleteRequest
 * @author: yu_wei
 * @create: 2024/10/27 00:01
 */
@Data
public class DeleteRequest implements Serializable {
    
    /**
     * id
     */
    private Long id;
    
    private static final long serialVersionUID = 1L;
}