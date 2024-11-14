package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Photo;
import com.wy.wydemo.model.vo.query.PhotoQuery;
import com.wy.wydemo.model.vo.response.PhotoBackResp;
import com.wy.wydemo.model.vo.response.PhotoResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: PhotoMapper
 * @author: yu_wei
 * @create: 2024/11/06 23:15
 */
@Mapper
public interface PhotoMapper extends BaseMapper<Photo> {
    /**
     * 查询后台照片列表
     *
     * @param photoQuery 照片查询条件
     * @return 后台照片列表
     */
    List<PhotoBackResp> selectBackPhotoList(@Param("param") PhotoQuery photoQuery);
    
    /**
     * 查询照片列表
     *
     * @param albumId 相册id
     * @return 后台照片列表
     */
    List<PhotoResp> selectPhotoVOList(@Param("albumId") Integer albumId);
}
