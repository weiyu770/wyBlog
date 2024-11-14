package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Album;
import com.wy.wydemo.model.vo.query.AlbumQuery;
import com.wy.wydemo.model.vo.request.AlbumReq;
import com.wy.wydemo.model.vo.response.AlbumBackResp;
import com.wy.wydemo.model.vo.response.AlbumResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: AlbumMapper
 * @author: yu_wei
 * @create: 2024/11/06 16:43
 */
@Mapper
public interface AlbumMapper extends BaseMapper<Album> {
    /**
     * 查询后台相册列表
     *
     * @param albumQuery 相册查询条件
     * @return 后台相册列表
     */
    List<AlbumBackResp> selectBackAlbumList(@Param("param") AlbumQuery albumQuery);
    
    /**
     * 根据id查询相册信息
     *
     * @param albumId 相册id
     * @return 相册
     */
    AlbumReq selectAlbumById(@Param("albumId") Integer albumId);
    
    
    /**
     * 查询相册列表
     *
     * @return 相册列表
     */
    List<AlbumResp> selectAlbumVOList();
    
    /**
     * 根据id查询照片相册信息
     *
     * @param albumId 相册id
     * @return 照片相册信息
     */
    AlbumBackResp selectAlbumInfoById(Integer albumId);

}
