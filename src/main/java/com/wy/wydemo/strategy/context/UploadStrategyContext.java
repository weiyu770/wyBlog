package com.wy.wydemo.strategy.context;

import com.wy.wydemo.strategy.UploadStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

import static com.wy.wydemo.model.enums.UploadModeEnum.getStrategy;

/**
 * @description: 上传策略上下文
 * @class: UploadStrategyContext
 * @author: yu_wei
 * @create: 2024/11/05 23:18
 */
@Service
@Slf4j
public class UploadStrategyContext {
    /**
     * 上传模式
     */
    @Value("${upload.strategy}")
    private String uploadStrategy;
    
    @Autowired
    private Map<String, UploadStrategy> uploadStrategyMap;
    
    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 路径
     * @return {@link String} 文件地址
     */
    public String executeUploadStrategy(MultipartFile file, String path) {
        return uploadStrategyMap.get(getStrategy(uploadStrategy)).uploadFile(file, path);
    }
    
}