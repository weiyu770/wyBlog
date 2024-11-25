package com.wy.wydemo.strategy.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wy.wydemo.constant.CommonConstant;
import com.wy.wydemo.constant.RedisConstant;
import com.wy.wydemo.mapper.ArticleMapper;
import com.wy.wydemo.model.entity.Article;
import com.wy.wydemo.service.impl.RedisService;
import com.wy.wydemo.strategy.LikeStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @description:抽象文章点赞策略
 * @class: ArticleLikeStrategyImpl
 * @author: yu_wei
 * @create: 2024/11/06 01:18
 */
@Service("articleLikeStrategyImpl")
@Slf4j
public class ArticleLikeStrategyImpl implements LikeStrategy {
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private ArticleMapper articleMapper;
    
    @Override
    public void like(Integer articleId) {
        log.info("开始处理文章点赞请求，文章ID: {}", articleId);
        
        // 判断文章是否存在或者是否进入回收站
        Article article = articleMapper.selectOne(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getIsDelete)
                .eq(Article::getId, articleId));
        
        // 检查文章是否存在
        Assert.isFalse(Objects.isNull(article) || article.getIsDelete().equals(CommonConstant.TRUE), "文章不存在");
        log.info("文章ID: {} 存在，且未被删除", articleId);
        
        // 用户id作为键，文章id作为值，记录用户点赞记录
        String userLikeArticleKey = RedisConstant.USER_ARTICLE_LIKE + StpUtil.getLoginIdAsLong();
        
        // 判断是否已点赞
        if (redisService.hasSetValue(userLikeArticleKey, articleId)) {
            log.info("用户已点赞文章ID: {}，即将取消点赞", articleId);
            
            // 取消点赞则删除用户id中的文章id
            redisService.deleteSet(userLikeArticleKey, articleId);
            
            // 文章点赞量-1
            redisService.decrHash(RedisConstant.ARTICLE_LIKE_COUNT, articleId.toString(), 1L);
            log.info("取消点赞成功，文章ID: {} 的点赞量已减少", articleId);
        } else {
            log.info("用户未点赞文章ID: {}，即将进行点赞", articleId);
            
            // 点赞则在用户id记录文章id
            redisService.setSet(userLikeArticleKey, articleId);
            
            // 文章点赞量+1
            redisService.incrHash(RedisConstant.ARTICLE_LIKE_COUNT, articleId.toString(), 1L);
            log.info("点赞成功，文章ID: {} 的点赞量已增加", articleId);
        }
    }
    
    @Override
    public void unlike(Integer articleId) {
        log.info("开始处理文章取消点赞请求，文章ID: {}", articleId);
        
        // 判断文章是否存在或者是否进入回收站
        Article article = articleMapper.selectOne(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getIsDelete)
                .eq(Article::getId, articleId));
        
        // 检查文章是否存在
        Assert.isFalse(Objects.isNull(article) || article.getIsDelete().equals(CommonConstant.TRUE), "文章不存在");
        log.info("文章ID: {} 存在，且未被删除", articleId);
        
        // 用户id作为键，文章id作为值，记录用户点赞记录
        String userLikeArticleKey = RedisConstant.USER_ARTICLE_LIKE + StpUtil.getLoginIdAsLong();
        
        // 判断是否已点赞
        if (!redisService.hasSetValue(userLikeArticleKey, articleId)) {
            log.info("用户未点赞文章ID: {}，无法取消点赞", articleId);
            return;  // 如果用户没有点赞，则不进行任何操作
        }
        
        // 取消点赞
        log.info("用户已点赞文章ID: {}，即将取消点赞", articleId);
        
        // 删除用户id中的文章id
        redisService.deleteSet(userLikeArticleKey, articleId);
        
        // 文章点赞量-1
        redisService.decrHash(RedisConstant.ARTICLE_LIKE_COUNT, articleId.toString(), 1L);
        log.info("取消点赞成功，文章ID: {} 的点赞量已减少", articleId);
    }
    
}