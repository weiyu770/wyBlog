package com.wy.wydemo.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 上传策略
 * @class: UploadStrategy
 * @author: yu_wei
 * @create: 2024/11/05 23:18
 */
public interface UploadStrategy {
    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 上传路径
     * @return {@link String} 文件地址
     */
    String uploadFile(MultipartFile file, String path);
}
