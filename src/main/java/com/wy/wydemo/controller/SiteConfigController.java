package com.wy.wydemo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.wy.wydemo.annotation.OptLogger;
import com.wy.wydemo.model.entity.SiteConfig;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.impl.SiteConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.wy.wydemo.constant.OptTypeConstant.*;

/**
 * @description: 网站配置管理
 * @class: SiteConfigController
 * @author: yu_wei
 * @create: 2024/11/08 16:41
 */
@Api(tags = "网站配置管理")
@RestController
public class SiteConfigController {
    
    @Autowired
    private SiteConfigService siteConfigService;
    
    /**
     * 获取网站配置
     *
     * @return {@link Result<SiteConfig>} 网站配置
     */
    @ApiOperation(value = "查询网站配置")
//    @SaCheckPermission("web:site:list")
    @GetMapping("/admin/site/list")
    public Result<SiteConfig> getSiteConfig() {
        return Result.success(siteConfigService.getSiteConfig());
    }
    
    /**
     * 更新网站配置
     *
     * @param siteConfig 网站配置
     * @return {@link Result<>}
     */
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "更新网站配置")
//    @SaCheckPermission("web:site:update")
    @PutMapping("/admin/site/update")
    public Result<?> updateSiteConfig(@RequestBody SiteConfig siteConfig) {
        siteConfigService.updateSiteConfig(siteConfig);
        return Result.success();
    }
    
    /**
     * 上传网站配置图片
     *
     * @param file 图片
     * @return {@link Result<String>} 图片路径
     */
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传网站配置图片")
    @ApiImplicitParam(name = "file", value = "配置图片", required = true, dataType = "MultipartFile")
//    @SaCheckPermission("web:site:upload")
    @PostMapping("/admin/site/upload")
    public Result<String> uploadSiteImg(@RequestParam("file") MultipartFile file) {
        return Result.success(siteConfigService.uploadSiteImg(file));
    }
    
}