package com.wy.wydemo.service.impl;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.mapper.AlbumMapper;
import com.wy.wydemo.mapper.PhotoMapper;
import com.wy.wydemo.model.entity.Album;
import com.wy.wydemo.model.entity.Photo;
import com.wy.wydemo.model.enums.FilePathEnum;
import com.wy.wydemo.model.vo.query.PhotoQuery;
import com.wy.wydemo.model.vo.request.PhotoInfoReq;
import com.wy.wydemo.model.vo.request.PhotoReq;
import com.wy.wydemo.model.vo.response.AlbumBackResp;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.model.vo.response.PhotoBackResp;
import com.wy.wydemo.model.vo.response.PhotoResp;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.BlogFileService;
import com.wy.wydemo.service.PhotoService;
import com.wy.wydemo.strategy.context.UploadStrategyContext;
import com.wy.wydemo.utils.BeanCopyUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @class: PhotoServiceImpl
 * @author: yu_wei
 * @create: 2024/11/08 16:00
 */
@Service
public class PhotoServiceImpl  extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {
    
    
    
    @Autowired
    private PhotoMapper photoMapper;
    
    @Autowired
    private AlbumMapper albumMapper;
    
    @Autowired
    private UploadStrategyContext uploadStrategyContext;
    
    @Autowired
    private BlogFileService blogFileService;

    
    /**
     * 分页查看照片后台列表
     * @param photoQuery
     * @return
     */
    @Override
    public PageResult<PhotoBackResp> listPhotoBackVO(PhotoQuery photoQuery) {
        // 查询照片数量
        Long count = photoMapper.selectCount(new LambdaQueryWrapper<Photo>()
                .eq(Objects.nonNull(photoQuery.getAlbumId()), Photo::getAlbumId, photoQuery.getAlbumId()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询照片列表
        List<PhotoBackResp> photoList = photoMapper.selectBackPhotoList(photoQuery);
        return new PageResult<>(photoList, count);
    }
    
    
    /**
     * ID查询相册信息
     * @param albumId
     * @return
     */
    @Override
    public AlbumBackResp getAlbumInfo(Integer albumId) {
        AlbumBackResp albumBackResp = albumMapper.selectAlbumInfoById(albumId);
        if (Objects.isNull(albumBackResp)) {
            return null;
        }
        Long photoCount = photoMapper.selectCount(new LambdaQueryWrapper<Photo>()
                .eq(Photo::getAlbumId, albumId));
        albumBackResp.setPhotoCount(photoCount);
        return albumBackResp;
    }
    
    @Override
    public String uploadPhoto(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.PHOTO.getPath());
        blogFileService.saveBlogFile(file, url, FilePathEnum.PHOTO.getFilePath());
        return url;
    }
    
    
    /**
     * 添加照片
     * @param photo
     */
    @Override
    public void addPhoto(PhotoReq photo) {
        // 批量保存照片
        List<Photo> pictureList = photo.getPhotoUrlList().stream()
                .map(url -> Photo.builder()
                        .albumId(photo.getAlbumId())
                        .photoName(IdWorker.getIdStr())
                        .photoUrl(url)
                        .build())
                .collect(Collectors.toList());
        this.saveBatch(pictureList);
    }
    
    
    /**
     * 修改照片信息
     * @param photoInfo
     */
    @Override
    public void updatePhoto(PhotoInfoReq photoInfo) {
        Photo photo = BeanCopyUtils.copyBean(photoInfo, Photo.class);
        baseMapper.updateById(photo);
    }
    
    
    /**
     * 删除照片
     * @param photoIdList
     */
    @Override
    public void deletePhoto(List<Integer> photoIdList) {
        baseMapper.deleteBatchIds(photoIdList);
    }
    
    @Override
    public void movePhoto(PhotoReq photo) {
        List<Photo> photoList = photo.getPhotoIdList().stream()
                .map(photoId -> Photo.builder()
                        .id(photoId)
                        .albumId(photo.getAlbumId())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(photoList);
    }
    
    /**
     * 查看照片列表
     * @param albumId
     * @return
     */
    @Override
    public Map<String, Object> listPhotoVO(Integer albumId) {
        Map<String, Object> result = new HashMap<>(2);
        String albumName = albumMapper.selectOne(new LambdaQueryWrapper<Album>()
                        .select(Album::getAlbumName).eq(Album::getId, albumId))
                .getAlbumName();
        List<PhotoResp> photoVOList = photoMapper.selectPhotoVOList(albumId);
        result.put("albumName", albumName);
        result.put("photoVOList", photoVOList);
        return result;
    }
    
    
}
