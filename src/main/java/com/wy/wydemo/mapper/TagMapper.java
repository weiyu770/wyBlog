package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Tag;
import com.wy.wydemo.model.vo.query.TagQuery;
import com.wy.wydemo.model.vo.response.TagBackResp;
import com.wy.wydemo.model.vo.response.TagOptionResp;
import com.wy.wydemo.model.vo.response.TagResp;
import org.apache.ibatis.annotations.Mapper;
import com.wy.wydemo.model.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 标签 Mapper
 * @class: TagMapper
 * @author: yu_wei
 * @create: 2024/11/05 10:52
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询文章标签名称
     *
     * @param articleId 文章id
     * @return 文章标签名称
     */
    List<String> selectTagNameByArticleId(@Param("articleId") Integer articleId);
    
    
    
    /**
     * 查询标签列表
     *
     * @return 标签列表
     */
    List<TagOptionResp> selectTagOptionList();
    
    /**
     * 查询后台标签列表
     *
     * @param tagQuery 标签查询条件
     * @return 后台标签列表
     */
    List<TagBackResp> selectBackTagList(@Param("param") TagQuery tagQuery);
    
    /**
     * 查询文章标签列表
     *
     * @return 文章标签列表
     */
    List<TagResp> selectTagVOList();
}
