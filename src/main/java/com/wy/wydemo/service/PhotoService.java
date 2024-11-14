package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Photo;
import com.wy.wydemo.model.vo.query.PhotoQuery;
import com.wy.wydemo.model.vo.request.PhotoInfoReq;
import com.wy.wydemo.model.vo.request.PhotoReq;
import com.wy.wydemo.model.vo.response.AlbumBackResp;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.model.vo.response.PhotoBackResp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @class: PhotoService
 * @author: yu_wei
 * @create: 2024/11/08 16:00
 */

public interface PhotoService extends IService<Photo> {
    
    /**
     * 查看后台照片列表
     * @param photoQuery
     * @return
     */
    PageResult<PhotoBackResp> listPhotoBackVO(PhotoQuery photoQuery);
    
    
    /**
     * 查看照片相册信息
     * @param albumId
     * @return
     */
    AlbumBackResp getAlbumInfo(Integer albumId);
    
    
    /**
     * 上传照片
     * @param file
     * @return
     */
    String uploadPhoto(MultipartFile file);
    
    
    /**
     * 添加照片
     * @param photo
     */
    void addPhoto(PhotoReq photo);
    
    
    /**
     * 修改照片信息
     * @param photoInfo
     */
    void updatePhoto(PhotoInfoReq photoInfo);
    
    
    /**
     * 删除照片信息
     * @param photoIdList
     */
    void deletePhoto(List<Integer> photoIdList);
    
    
    /**
     * 移动照片
     * @param photo
     */
    void movePhoto(PhotoReq photo);
    
    /**
     * 查看照片列表
     * @param albumId
     * @return
     */
    Map<String, Object> listPhotoVO(Integer albumId);
}
