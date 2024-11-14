package com.wy.wydemo.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.mapper.PhotoMapper;
import com.wy.wydemo.model.entity.Photo;
import com.wy.wydemo.model.enums.FilePathEnum;
import com.wy.wydemo.model.vo.query.AlbumQuery;
import com.wy.wydemo.model.vo.request.AlbumReq;
import com.wy.wydemo.model.vo.response.AlbumBackResp;
import com.wy.wydemo.model.vo.response.AlbumResp;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.service.BlogFileService;
import com.wy.wydemo.strategy.context.UploadStrategyContext;
import com.wy.wydemo.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;
import com.wy.wydemo.mapper.AlbumMapper;
import com.wy.wydemo.model.entity.Album;
import com.wy.wydemo.service.AlbumService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @description: 专辑服务实现类
 * @class: AlbumServiceImpl
 * @author: yu_wei
 * @create: 2024/11/06 16:41
 */
@Service
@Slf4j
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {
    
    @Autowired
    private AlbumMapper albumMapper;
    
    
    @Autowired
    private UploadStrategyContext uploadStrategyContext;
    
    
    @Autowired
    private BlogFileService blogFileService;
    
    
    @Autowired
    private PhotoMapper photoMapper;
    
    @Override
    public PageResult<AlbumBackResp> listAlbumBackVO(AlbumQuery albumQuery) {
        // 查询相册数量
        Long count = albumMapper.selectCount(new LambdaQueryWrapper<Album>()
                .like(StringUtils.hasText(albumQuery.getKeyword()), Album::getAlbumName, albumQuery.getKeyword()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询相册信息
        List<AlbumBackResp> albumList = albumMapper.selectBackAlbumList(albumQuery);
        return new PageResult<>(albumList, count);
    }
    
    @Override
    public String uploadAlbumCover(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.PHOTO.getPath());
        blogFileService.saveBlogFile(file, url, FilePathEnum.PHOTO.getFilePath());
        return url;
    }
    
    /**
     * 添加信息
     * @param album
     */
    @Override
    public void addAlbum(AlbumReq album) {
        // 相册是否存在
        Album existAlbum = albumMapper.selectOne(new LambdaQueryWrapper<Album>()
                .select(Album::getId)
                .eq(Album::getAlbumName, album.getAlbumName()));
        Assert.isNull(existAlbum, album.getAlbumName() + "相册已存在");
        // 添加新相册
        Album newAlbum = BeanCopyUtils.copyBean(album, Album.class);
        baseMapper.insert(newAlbum);
    }
    
    /**
     * 删除相册
     * @param albumId
     */
    @Override
    public void deleteAlbum(Integer albumId) {
        // 查询照片数量
        Long count = photoMapper.selectCount(new LambdaQueryWrapper<Photo>()
                .eq(Photo::getAlbumId, albumId));
        Assert.isFalse(count > 0, "相册下存在照片");
        // 不存在照片则删除
        albumMapper.deleteById(albumId);
    }
    
    /**
     * 修改相册
     * @param album
     */
    @Override
    public void updateAlbum(AlbumReq album) {
        // 检查相册是否存在
        Album existAlbum = albumMapper.selectOne(new LambdaQueryWrapper<Album>()
                .select(Album::getId)
                .eq(Album::getAlbumName, album.getAlbumName()));
        if (Objects.nonNull(existAlbum) && !existAlbum.getId().equals(album.getId())) {
            log.warn("尝试更新的相册名称 {} 已存在", album.getAlbumName());
            Assert.isFalse(true, album.getAlbumName() + "相册已存在");
        }
        // 修改相册
        Album newAlbum = BeanCopyUtils.copyBean(album, Album.class);
        int result = baseMapper.updateById(newAlbum);
        if (result > 0) {
            log.info("相册 {} 更新成功", album.getAlbumName());
        } else {
            log.error("相册 {} 更新失败", album.getAlbumName());
        }
    }
    
    
    /**
     * 查询相册信息
     * @param albumId
     * @return
     */
    @Override
    public AlbumReq editAlbum(Integer albumId) {
        log.debug("开始查询相册信息，相册ID: {}", albumId);
        AlbumReq album = albumMapper.selectAlbumById(albumId);
        
        if (album != null) {
            log.info("查询到相册信息: {}", album);
        } else {
            log.warn("未找到相册ID为 {} 的相册信息", albumId);
        }
        return album;
    }
    
    
    /**
     * 查询相册列表
     * @return
     */
    @Override
    public List<AlbumResp> listAlbumVO() {
        List<AlbumResp> albumList = albumMapper.selectAlbumVOList();
        if (albumList != null && !albumList.isEmpty()) {
            log.info("查询到 {} 条相册信息", albumList.size());
        } else {
            log.warn("未查询到任何相册信息");
        }
        return albumList;
    }

    
}
