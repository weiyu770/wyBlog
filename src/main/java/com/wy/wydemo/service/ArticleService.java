package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Article;
import com.wy.wydemo.model.vo.request.RecommendReq;
import com.wy.wydemo.model.vo.query.PageQuery;
import com.wy.wydemo.model.vo.response.*;
import com.wy.wydemo.model.vo.request.DeleteReq;
import com.wy.wydemo.model.vo.query.ArticleQuery;
import com.wy.wydemo.model.vo.request.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @description:  文章服务
 * @class: ArticleService
 * @author: yu_wei
 * @create: 2024/11/05 10:21
 */
public interface ArticleService extends IService<Article> {
    
    /**
     * 查看后台文章列表
     * @param articleQuery
     * @return
     */
    PageResult<ArticleBackResp> listArticleBackVO(ArticleQuery articleQuery);
    
    /**
     * 添加文章
     * @param article
     */
    void addArticle(ArticleReq article);
    
    /**
     * 删除文章
     * @param articleIdList
     */
    void deleteArticle(List<Integer> articleIdList);
    
    
    /**
     * 更新文章删除状态
     * @param delete
     */
    void updateArticleDelete(DeleteReq delete);
    
    /**
     * 修改文章
     * @param article
     */
    void updateArticle(ArticleReq article);
    
    
    /**
     * 编辑文章
     * @param articleId
     * @return
     */
    ArticleInfoResp editArticle(Integer articleId);
    
    
    /**
     * 上传文章图片
     * @param file
     * @return
     */
    String saveArticleImages(MultipartFile file);
    
    /**
     * 置顶信息
     * @param top
     */
    void updateArticleTop(TopReq top);
    
    /**
     * 推荐文章
     * @param recommend
     */
    void updateArticleRecommend(RecommendReq recommend);
    
    /**
     * 搜索文章
     * @param keyword
     * @return
     */
    List<ArticleSearchResp> listArticlesBySearch(String keyword);
    
    /**
     * 查看首页文章列表
     * @param pageQuery
     * @return
     */
    PageResult<ArticleHomeResp> listArticleHomeVO(PageQuery pageQuery);
    
    /**
     * 查看文章
     * @param articleId
     * @return
     */
    ArticleResp getArticleHomeById(Integer articleId);
    
    /**
     * 查看推荐文章
     * @return
     */
    List<ArticleRecommendResp> listArticleRecommendVO();
    
    /**
     * 查看文章归档
     * @param pageQuery
     * @return
     */
    PageResult<ArchiveResp> listArchiveVO(PageQuery pageQuery);
    
    
    /**
     * 查询文章排行榜
     * @param articleMap
     * @return
     */
    List<ArticleRankResp> listArticleRank(Map<Object, Double> articleMap);
}
