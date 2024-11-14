package com.wy.wydemo.controller;

import com.wy.wydemo.annotation.VisitLogger;
import com.wy.wydemo.model.vo.response.BlogBackInfoResp;
import com.wy.wydemo.model.vo.response.BlogInfoResp;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.BlogInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:博客控制器
 * @class: BlogInfoController
 * @author: yu_wei
 * @create: 2024/11/07 17:03
 */
@Api(tags = "BLOG管理")
@RestController
public class BlogInfoController {
    
    
    @Autowired
    private BlogInfoService blogInfoService;
    
    /**
     * 上传访客信息
     *
     * @return {@link Result<>}
     */
    @ApiOperation(value = "上传访客信息")
    @PostMapping("/report")
    public Result<?> report() {
        blogInfoService.report();
        
        return Result.success();
    }
    
    /**
     * 查看博客信息
     *
     * @return {@link Result< BlogInfoResp >} 博客信息
     */
    @ApiOperation(value = "查看博客信息")
    //@TableName("t_site_config")
    @GetMapping("/")
    public Result<BlogInfoResp> getBlogInfo() {
        return Result.success(blogInfoService.getBlogInfo());
    }
    
    /**
     * 查看博客后台信息
     *
     * @return {@link Result< BlogBackInfoResp >} 后台信息
     */
    //TODO
    @ApiOperation(value = "查看后台信息")
    @GetMapping("/admin")
    public Result<BlogBackInfoResp> getBlogBackInfo() {
        return Result.success(blogInfoService.getBlogBackInfo());
    }
    
    /**
     * 查看关于我信息
     *
     * @return {@link Result<String>} 关于我信息
     */
    @VisitLogger(value = "关于")
    @ApiOperation(value = "查看关于我信息")
    @GetMapping("/about")
    public Result<String> getAbout() {
        return Result.success(blogInfoService.getAbout());
    }
    
    
    
    
    
}
