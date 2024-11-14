package com.wy.wydemo.strategy;

import com.wy.wydemo.model.vo.response.ArticleSearchResp;

import java.util.List;

/**
 * @description:文章搜索策略
 * @class: SearchStrategy
 * @author: yu_wei
 * @create: 2024/11/06 10:40
 */
public interface SearchStrategy {
    
    /**
     * 搜索文章
     *
     * @param keyword 关键字
     * @return {@link List < ArticleSearchResp >} 文章列表
     */
    List<ArticleSearchResp> searchArticle(String keyword);
}
