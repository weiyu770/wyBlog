package com.wy.wydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.constant.RedisConstant;
import com.wy.wydemo.mapper.SiteConfigMapper;
import com.wy.wydemo.model.entity.SiteConfig;
import com.wy.wydemo.model.enums.FilePathEnum;
import com.wy.wydemo.service.BlogFileService;
import com.wy.wydemo.strategy.context.UploadStrategyContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * @description: 网站配置服务
 * @class: SiteConfigService
 * @author: yu_wei
 * @create: 2024/11/07 17:16
 */
@Slf4j
@Service
public class SiteConfigService extends ServiceImpl<SiteConfigMapper, SiteConfig> {
    
    @Autowired
    private SiteConfigMapper siteConfigMapper;
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private UploadStrategyContext uploadStrategyContext;
    
    @Autowired
    private BlogFileService blogFileService;
    
    public SiteConfig getSiteConfig() {
        SiteConfig siteConfig = redisService.getObject(RedisConstant.SITE_SETTING);
        if (Objects.isNull(siteConfig)) {
            // 从数据库中加载
            siteConfig = siteConfigMapper.selectById(1);
            redisService.setObject(RedisConstant.SITE_SETTING, siteConfig);
        }
        return siteConfig;
    }
    
    public void updateSiteConfig(SiteConfig siteConfig) {
        baseMapper.updateById(siteConfig);
        redisService.deleteObject(RedisConstant.SITE_SETTING);
    }
    
    public String uploadSiteImg(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.CONFIG.getPath());
        blogFileService.saveBlogFile(file, url, FilePathEnum.CONFIG.getFilePath());
        return url;
    }
}




