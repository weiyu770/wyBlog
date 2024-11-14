package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.BlogFile;
import com.wy.wydemo.model.vo.query.FileQuery;
import com.wy.wydemo.model.vo.response.FileResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: BlogFileMapper
 * @author: yu_wei
 * @create: 2024/11/05 23:22
 */
@Mapper
public interface BlogFileMapper extends BaseMapper<BlogFile> {
    
    /**
     * 查询后台文件列表
     *
     * @param fileQuery 文件条件
     * @return 后台文件列表
     */
    List<FileResp> selectFileVOList(@Param("param") FileQuery fileQuery);
}
