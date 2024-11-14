package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.BlogFile;
import com.wy.wydemo.model.vo.query.FileQuery;
import com.wy.wydemo.model.vo.query.PageQuery;
import com.wy.wydemo.model.vo.request.FolderReq;
import com.wy.wydemo.model.vo.response.FileResp;
import com.wy.wydemo.model.vo.response.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description:
 * @class: BlogFileService
 * @author: yu_wei
 * @create: 2024/11/05 23:20
 */
public interface BlogFileService extends IService<BlogFile> {
    
    
    /**
     * 上传文件
     * @param file
     * @param url
     * @param filePath
     */
    void saveBlogFile(MultipartFile file, String url, String filePath);
    
    /**
     * 查询文件列表
     * @param fileQuery
     * @return
     */
    PageResult<FileResp> listFileVOList(FileQuery fileQuery);
    
    
    /**
     * 上传文件
     * @param file
     * @param path
     */
    void uploadFile(MultipartFile file, String path);
    
    /**
     * 创建目录
     * @param folder
     */
    void createFolder(FolderReq folder);
    
    /**
     * 删除文件
     * @param fileIdList
     */
    void deleteFile(List<Integer> fileIdList);
    
    /**
     * 下载文件
     * @param fileId
     */
    void downloadFile(Integer fileId);
}
