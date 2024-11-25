package com.wy.wydemo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wy.wydemo.constant.CommonConstant;
import com.wy.wydemo.constant.RedisConstant;
import com.wy.wydemo.mapper.ArticleMapper;
import com.wy.wydemo.mapper.ArticleTagMapper;
import com.wy.wydemo.mapper.CategoryMapper;
import com.wy.wydemo.mapper.TagMapper;
import com.wy.wydemo.model.entity.*;
import com.wy.wydemo.model.enums.ArticleStatusEnum;
import com.wy.wydemo.model.enums.FilePathEnum;
import com.wy.wydemo.model.vo.request.RecommendReq;
import com.wy.wydemo.model.vo.query.PageQuery;
import com.wy.wydemo.model.vo.response.*;
import com.wy.wydemo.model.vo.request.DeleteReq;
import com.wy.wydemo.model.vo.query.ArticleQuery;
import com.wy.wydemo.model.vo.request.*;
import com.wy.wydemo.service.ArticleService;
import com.wy.wydemo.service.BlogFileService;
import com.wy.wydemo.service.TagService;
import com.wy.wydemo.strategy.context.SearchStrategyContext;
import com.wy.wydemo.strategy.context.UploadStrategyContext;
import com.wy.wydemo.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @description: 文章帖子服务实现
 * @class: ArticleServiceImpl
 * @author: yu_wei
 * @create: 2024/11/05 10:22
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
    
    @Autowired
    private ArticleMapper articleMapper;
    
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    
    @Autowired
    private ArticleTagMapper articleTagMapper;
    
    @Autowired
    private TagMapper tagMapper;
    
    
    @Autowired
    private TagService tagService;
    
    @Autowired
    private UploadStrategyContext uploadStrategyContext;
    
    
    @Autowired
    private BlogFileService blogFileService;
    
    
    @Autowired
    private SearchStrategyContext searchStrategyContext;
    
    /**
     * 查询后台文章列表
     *
     * @param articleQuery
     * @return
     */
    @Override
    public PageResult<ArticleBackResp> listArticleBackVO(ArticleQuery articleQuery) {
        // 查询文章数量
        Long count = articleMapper.selectBackArticleCount(articleQuery);
        if (count == 0) {
            log.info("查询结果为空，返回空的 PageResult");
            return new PageResult<>();
        }
        // 查询文章后台信息
        List<ArticleBackResp> articleBackRespList = articleMapper.selectBackArticleList(articleQuery);
        log.info("查询到 {} 条文章信息", articleBackRespList.size());
        // 浏览量
        Map<Object, Double> viewCountMap = redisService.getZsetAllScore(RedisConstant.ARTICLE_VIEW_COUNT);
        log.info("获取到 {} 条浏览量信息", viewCountMap.size());
        // 点赞量
        Map<String, Integer> likeCountMap = redisService.getHashAll(RedisConstant.ARTICLE_LIKE_COUNT);
        log.info("获取到 {} 条点赞量信息", likeCountMap.size());
        // 封装文章后台信息
        articleBackRespList.forEach(item -> {
            Double viewCount = Optional.ofNullable(viewCountMap.get(item.getId())).orElse((double) 0);
            item.setViewCount(viewCount.intValue());
            Integer likeCount = likeCountMap.get(item.getId().toString());
            item.setLikeCount(Optional.ofNullable(likeCount).orElse(0));
        });
        return new PageResult<>(articleBackRespList, count);
    }
    
    /**
     * 添加文章
     *
     * @param article
     */
    @Transactional(rollbackFor = Exception.class)
    public void addArticle(ArticleReq article) {
        // 保存文章分类
        Integer categoryId = saveArticleCategory(article);
        // 添加文章
        Article newArticle = BeanCopyUtils.copyBean(article, Article.class);
        if (StringUtils.isBlank(newArticle.getArticleCover())) {
            SiteConfig siteConfig = redisService.getObject(RedisConstant.SITE_SETTING);
            newArticle.setArticleCover(siteConfig.getArticleCover());
        }
        newArticle.setCategoryId(categoryId);
        newArticle.setUserId(StpUtil.getLoginIdAsLong());
        baseMapper.insert(newArticle);
        // 保存文章标签
        saveArticleTag(article, newArticle.getId());
    }
    
    
    /**
     * 保存文章标签
     *
     * @param article   文章信息
     * @param articleId 文章id
     */
    private void saveArticleTag(ArticleReq article, Integer articleId) {
        // 删除文章标签
        articleTagMapper.delete(new LambdaQueryWrapper<ArticleTag>()
                .eq(ArticleTag::getArticleId, articleId));
        // 标签名列表
        List<String> tagNameList = article.getTagNameList();
        if (CollectionUtils.isEmpty(tagNameList)) {
            return;
        }
        // 查询出已存在的标签
        List<Tag> existTagList = tagMapper.selectList(new LambdaQueryWrapper<Tag>()
                .select(Tag::getId, Tag::getTagName)
                .in(Tag::getTagName, tagNameList));
        List<String> existTagNameList = existTagList.stream()
                .map(Tag::getTagName)
                .collect(Collectors.toList());
        List<Integer> existTagIdList = existTagList.stream()
                .map(Tag::getId)
                .collect(Collectors.toList());
        // 移除已存在的标签列表
        tagNameList.removeAll(existTagNameList);
        // 含有新标签
        if (CollectionUtils.isNotEmpty(tagNameList)) {
            // 新标签列表
            List<Tag> newTagList = tagNameList.stream()
                    .map(item -> Tag.builder()
                            .tagName(item)
                            .build())
                    .collect(Collectors.toList());
            // 批量保存新标签
            tagService.saveBatch(newTagList);
            // 获取新标签id列表
            List<Integer> newTagIdList = newTagList.stream()
                    .map(Tag::getId)
                    .collect(Collectors.toList());
            // 新标签id添加到id列表
            existTagIdList.addAll(newTagIdList);
        }
        // 将所有的标签绑定到文章标签关联表
        articleTagMapper.saveBatchArticleTag(articleId, existTagIdList);
    }
    
    /**
     * 保存文章分类
     *
     * @param article 文章信息
     * @return 文章分类
     */
    private Integer saveArticleCategory(ArticleReq article) {
        // 查询分类
        Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, article.getCategoryName()));
        // 分类不存在
        if (Objects.isNull(category)) {
            category = Category.builder()
                    .categoryName(article.getCategoryName())
                    .build();
            // 保存分类
            categoryMapper.insert(category);
        }
        return category.getId();
    }
    
    
    /**
     * 删除文章
     *
     * @param articleIdList 要删除的文章ID列表
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(List<Integer> articleIdList) {
        // 第一步：删除文章标签关系表中与文章关联的记录
        articleTagMapper.delete(new LambdaQueryWrapper<ArticleTag>()
                .in(ArticleTag::getArticleId, articleIdList));
        
        // 第二步：删除文章表中的记录
        articleMapper.deleteBatchIds(articleIdList);
        
        // 第三步：删除标签表中不再被使用的标签
        // 找出没有任何文章关联的标签
        List<Integer> unusedTagIds = tagMapper.selectList(new LambdaQueryWrapper<Tag>()
                        .notExists("SELECT 1 FROM t_article_tag WHERE tag_id = id"))
                .stream()
                .map(Tag::getId)
                .collect(Collectors.toList());
        
        // 如果存在未使用的标签，则删除它们
        if (!unusedTagIds.isEmpty()) {
            tagMapper.deleteBatchIds(unusedTagIds);
        }
    }
    
    
    /**
     * 更新文章删除状态
     *
     * @param delete
     */
    public void updateArticleDelete(DeleteReq delete) {
        // 批量更新文章删除状态
        List<Article> articleList = delete.getIdList()
                .stream()
                .map(id -> Article.builder()
                        .id(id)
                        .isDelete(delete.getIsDelete())
                        .isTop(CommonConstant.FALSE)
                        .isRecommend(CommonConstant.FALSE)
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(articleList);
    }
    
    /**
     * 修改文章
     *
     * @param article
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(ArticleReq article) {
        // 保存文章分类
        Integer categoryId = saveArticleCategory(article);
        // 修改文章
        Article newArticle = BeanCopyUtils.copyBean(article, Article.class);
        newArticle.setCategoryId(categoryId);
        newArticle.setUserId(StpUtil.getLoginIdAsLong());
        baseMapper.updateById(newArticle);
        // 保存文章标签
        saveArticleTag(article, newArticle.getId());
    }
    
    
    /**
     * 查看文章
     *
     * @param articleId
     * @return
     */
    @Override
    public ArticleInfoResp editArticle(Integer articleId) {
        // 查询文章信息
        ArticleInfoResp articleInfoVO = articleMapper.selectArticleInfoById(articleId);
        Assert.notNull(articleInfoVO, "没有该文章");
        // 查询文章分类名称
        Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getCategoryName)
                .eq(Category::getId, articleInfoVO.getCategoryId()));
        // 查询文章标签名称
        List<String> tagNameList = tagMapper.selectTagNameByArticleId(articleId);
        articleInfoVO.setCategoryName(category.getCategoryName());
        articleInfoVO.setTagNameList(tagNameList);
        return articleInfoVO;
    }
    
    /**
     * 上传文章图片
     *
     * @param file
     * @return
     */
    @Override
    public String saveArticleImages(MultipartFile file) {
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.ARTICLE.getPath());
        blogFileService.saveBlogFile(file, url, FilePathEnum.ARTICLE.getFilePath());
        return url;
    }
    
    @Override
    public void updateArticleTop(TopReq top) {
        log.info("开始更新文章置顶状态, 文章ID: {}, 置顶状态: {}", top.getId(), top.getIsTop());
        
        // 修改文章置顶状态
        Article newArticle = Article.builder()
                .id(top.getId())
                .isTop(top.getIsTop())
                .build();
        
        int rowsAffected = articleMapper.updateById(newArticle);
        
        if (rowsAffected > 0) {
            log.info("文章置顶状态更新成功, 文章ID: {}, 新置顶状态: {}", top.getId(), top.getIsTop());
        } else {
            log.warn("文章置顶状态更新失败, 文章ID: {}", top.getId());
        }
    }
    
    @Override
    public void updateArticleRecommend(RecommendReq recommend) {
        // 修改文章推荐状态
        log.info("开始更新文章推荐状态, 文章ID: {}, 推荐状态: {}", recommend.getId(), recommend.getIsRecommend());
        
        // 修改文章推荐状态
        Article newArticle = Article.builder()
                .id(recommend.getId())
                .isRecommend(recommend.getIsRecommend())
                .build();
        
        int rowsAffected = articleMapper.updateById(newArticle);
        
        if (rowsAffected > 0) {
            log.info("文章推荐状态更新成功, 文章ID: {}, 新推荐状态: {}", recommend.getId(), recommend.getIsRecommend());
        } else {
            log.warn("文章推荐状态更新失败, 文章ID: {}", recommend.getId());
        }
    }
    
    /**
     * 搜索文章
     * @param keyword
     * @return
     */
    @Override
    public List<ArticleSearchResp> listArticlesBySearch(String keyword) {
        return searchStrategyContext.executeSearchStrategy(keyword);
    }
    
    /**
     * 查看首页文章列表
     *
     * @param pageQuery
     * @return
     */
    @Override
    public PageResult<ArticleHomeResp> listArticleHomeVO(PageQuery pageQuery) {
        // 查询文章数量
        Long count = articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                .eq(Article::getIsDelete, CommonConstant.FALSE)
                .eq(Article::getStatus, ArticleStatusEnum.PUBLIC.getStatus()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询首页文章
        List<ArticleHomeResp> articleHomeVOList = articleMapper.selectArticleHomeList(pageQuery);
        return new PageResult<>(articleHomeVOList, count);
    }
    
    
    /**
     * 查看文章信息
     * @param articleId
     * @return
     */
    @Override
    public ArticleResp getArticleHomeById(Integer articleId) {
        log.info("开始获取文章详情，文章ID：{}", articleId); // 日志开始获取文章
        
        // 查询文章信息
        ArticleResp article = articleMapper.selectArticleHomeById(articleId);
        if (Objects.isNull(article)) {
            log.warn("未找到ID为{}的文章", articleId); // 文章未找到
            return null;
        }
        
        log.info("成功查询到文章：{}", article); // 文章查询成功，记录文章信息
        
        // 浏览量+1
        log.info("增加文章ID为{}的浏览量", articleId);
        redisService.incrZet(RedisConstant.ARTICLE_VIEW_COUNT, articleId, 1D);
        
        // 查询上一篇文章
        ArticlePaginationResp lastArticle = articleMapper.selectLastArticle(articleId);
        if (lastArticle != null) {
            log.info("查询到上一篇文章：{}", lastArticle);
        } else {
            log.info("未找到文章ID为{}的上一篇文章", articleId);
        }
        
        // 查询下一篇文章
        ArticlePaginationResp nextArticle = articleMapper.selectNextArticle(articleId);
        if (nextArticle != null) {
            log.info("查询到下一篇文章：{}", nextArticle);
        } else {
            log.info("未找到文章ID为{}的下一篇文章", articleId);
        }
        
        article.setLastArticle(lastArticle);
        article.setNextArticle(nextArticle);
        
        // 查询浏览量
        Double viewCount = Optional.ofNullable(redisService.getZsetScore(RedisConstant.ARTICLE_VIEW_COUNT, articleId))
                .orElse((double) 0);
        log.info("文章ID为{}的浏览量：{}", articleId, viewCount);
        
        article.setViewCount(viewCount.intValue());
        
        // 查询点赞量
        Integer likeCount = redisService.getHash(RedisConstant.ARTICLE_LIKE_COUNT, articleId.toString());
        log.info("文章ID为{}的点赞量：{}", articleId, likeCount);
        
        article.setLikeCount(Optional.ofNullable(likeCount).orElse(0));
        
        log.info("返回文章详情：{}", article); // 最后返回文章信息
        return article;
    }
    
    
    /**
     * 查看推荐文章
     * @return
     */
    @Override
    public List<ArticleRecommendResp> listArticleRecommendVO() {
        return articleMapper.selectArticleRecommend();
    }
    
    
    /**
     * 查看文章归档
     *
     * @param pageQuery
     * @return
     */
    //TODO
    @Override
    public PageResult<ArchiveResp> listArchiveVO(PageQuery pageQuery) {
        // 查询文章数量
        Long count = articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                .eq(Article::getIsDelete, CommonConstant.FALSE)
                .eq(Article::getStatus, ArticleStatusEnum.PUBLIC.getStatus()));
        if (count == 0) {
            return new PageResult<>();
        }
        List<ArchiveResp> archiveList = articleMapper.selectArchiveList(pageQuery);
        return new PageResult<>(archiveList, count);
    }
    
    @Override
    /**
     * 查询文章排行
     *
     * @param articleMap 文章浏览量信息
     * @return {@link List< ArticleRankResp >} 文章排行
     */
    public List<ArticleRankResp> listArticleRank(Map<Object, Double> articleMap) {
        // 提取文章id
        List<Integer> articleIdList = new ArrayList<>(articleMap.size());
        articleMap.forEach((key, value) -> articleIdList.add((Integer) key));
        // 查询文章信息
        List<Article> articleList = articleMapper.selectList(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getArticleTitle)
                .in(Article::getId, articleIdList));
        return articleList.stream()
                .map(article -> ArticleRankResp.builder()
                        .articleTitle(article.getArticleTitle())
                        .viewCount(articleMap.get(article.getId()).intValue())
                        .build())
                .sorted(Comparator.comparingInt(ArticleRankResp::getViewCount).reversed())
                .collect(Collectors.toList());
    }
    
    /**
     * 分页查询文章列表
     * @param pageQuery 分页查询参数
     * @return 分页结果
     */
    @Override
    public PageResult<ArticleHomeResp> listArticlesWithPagination(PageQuery pageQuery) {
        // 设置分页参数
        com.github.pagehelper.Page<Object> page = PageHelper.startPage(pageQuery.getCurrent(), pageQuery.getSize());
        
        // 执行查询
        List<ArticleHomeResp> articles = articleMapper.selectArticlesWithPagination(pageQuery.getCurrent(), pageQuery.getSize());
        
        // 将查询结果封装到 PageResult 中
        return new PageResult<>(articles, page.getTotal());
    }

    
    
}
