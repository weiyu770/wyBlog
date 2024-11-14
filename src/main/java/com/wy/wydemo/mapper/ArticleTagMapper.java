package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.ArticleTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 文章标签 Mapper
 * @class: ArticleTagMapper
 * @author: yu_wei
 * @create: 2024/11/05 10:51
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
    /**
     * 批量保存文章标签
     *
     * @param articleId 文章id
     * @param tagIdList 标签id列表
     */
    void saveBatchArticleTag(@Param("articleId") Integer articleId, List<Integer> tagIdList);
    
    
    
}
