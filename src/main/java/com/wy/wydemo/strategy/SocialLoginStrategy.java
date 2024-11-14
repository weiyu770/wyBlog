package com.wy.wydemo.strategy;

import com.wy.wydemo.model.vo.request.CodeReq;


/**
 * @description:
 * @class: SocialLoginStrategy
 * @author: yu_wei
 * @create: 2024/11/04 15:15
 */

//这个接口定义了不同第三方登录策略所需的方法。在这里，它定义了一个 login 方法
// 接收 CodeReq uest 类型的参数并返回一个 String 类型的 Token。
// 每种登录方式（如 QQ、Gitee、Github）都可以实现这个接口，提供各自的登录逻辑。
public interface SocialLoginStrategy {
    
    /**
     * 登录
     *
     * @param codeRequest uest 第三方code
     * @return  String  Token
     */
    String login(CodeReq codeRequest);
}
