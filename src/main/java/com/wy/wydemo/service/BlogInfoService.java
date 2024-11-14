package com.wy.wydemo.service;

import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.constant.RedisConstant;
import com.wy.wydemo.model.vo.response.BlogBackInfoResp;
import com.wy.wydemo.model.vo.response.BlogInfoResp;
import com.wy.wydemo.service.impl.RedisService;
import com.wy.wydemo.utils.UserAgentUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description:
 * @class: BlogInfoService
 * @author: yu_wei
 * @create: 2024/11/07 17:04
 */

public interface BlogInfoService extends  IService<T>{
    
    
    /**
     * 访问访客信息
     */
    void report();
    
    /**
     * 查看博客信息
     * @return
     */
    BlogInfoResp getBlogInfo();
    
    /**
     * 查看后台信息
     * @return
     */
    BlogBackInfoResp getBlogBackInfo();
    
    
    /**
     * 查看关于我的信息
     * @return
     */
    String getAbout();
    
    
}
