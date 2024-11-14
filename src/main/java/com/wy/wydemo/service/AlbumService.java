package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Album;
import com.wy.wydemo.model.entity.Article;
import com.wy.wydemo.model.vo.query.AlbumQuery;
import com.wy.wydemo.model.vo.request.AlbumReq;
import com.wy.wydemo.model.vo.response.AlbumBackResp;
import com.wy.wydemo.model.vo.response.AlbumResp;
import com.wy.wydemo.model.vo.response.PageResult;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description:
 * @class: AlbumService
 * @author: yu_wei
 * @create: 2024/11/06 16:40
 */
public interface AlbumService extends IService<Album> {
    
    /**
     * 查看后台相册列表
     * @param albumQuery
     * @return
     */
    PageResult<AlbumBackResp> listAlbumBackVO(AlbumQuery albumQuery);
    
    /**
     * 上传相册封面
     * @param file
     * @return
     */
    String uploadAlbumCover(MultipartFile file);
    
    /**
     * 添加信息
     * @param album
     */
    void addAlbum(AlbumReq album);
    
    /**
     * 删除相册
     * @param albumId
     */
    void deleteAlbum(Integer albumId);
    
    /**
     * 修改相册
     * @param album
     */
    void updateAlbum(AlbumReq album);
    
    
    /**
     * 编辑相册
     * @param albumId
     * @return
     */
    AlbumReq editAlbum(Integer albumId);
    
    
    /**
     * 查看相册列表
     * @return
     */
    List<AlbumResp> listAlbumVO();
    
    
}
