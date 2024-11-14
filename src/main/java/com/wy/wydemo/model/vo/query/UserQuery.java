package com.wy.wydemo.model.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @class: UserQuery
 * @author: yu_wei
 * @create: 2024/11/04 18:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户查询条件")
public class UserQuery extends PageQuery {
    
    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容")
    private String keyword;
    
    /**
     * 登录方式 (1邮箱 2QQ 3Gitee 4Github 5用户注册测试)
     */
    @ApiModelProperty(value = "登录方式 (1邮箱 2QQ 3Gitee 4Github)")
    private Integer loginType;
    
}