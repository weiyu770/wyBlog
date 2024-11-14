package com.wy.wydemo.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wy.wydemo.constant.CommonConstant;
import com.wy.wydemo.constant.RedisConstant;
import com.wy.wydemo.mapper.*;
import com.wy.wydemo.model.entity.Article;
import com.wy.wydemo.model.entity.SiteConfig;
import com.wy.wydemo.model.enums.ArticleStatusEnum;
import com.wy.wydemo.model.vo.response.*;
import com.wy.wydemo.service.ArticleService;
import com.wy.wydemo.service.BlogInfoService;
import com.wy.wydemo.utils.UserAgentUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;

/**
 * @description:
 * @class: BlogInfoServiceImpl
 * @author: yu_wei
 * @create: 2024/11/07 17:08
 */
@Service
@Slf4j
public class BlogInfoServiceImpl implements BlogInfoService {
    
    @Autowired
    private ArticleMapper articleMapper;
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Autowired
    private TagMapper tagMapper;
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private SiteConfigService siteConfigService;
    
    @Autowired
    private MessageMapper messageMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private VisitLogMapper visitLogMapper;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private ArticleService articleService;
    
    /**
     * 访客信息
     */
    @Override
    public void report() {
        // 获取用户IP
        String ipAddress = ServletUtil.getClientIP(request);
        log.info("获取到用户IP: {}", ipAddress);
        
        Map<String, String> userAgentMap = UserAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));
        String browser = userAgentMap.get("browser");
        String os = userAgentMap.get("os");
        log.info("用户访问设备: 浏览器 = {}, 操作系统 = {}", browser, os);
        
        // 生成唯一用户标识
        String uuid = ipAddress + browser + os;
        String md5 = DigestUtils.md5DigestAsHex(uuid.getBytes());
        log.debug("生成的用户唯一标识 (MD5): {}", md5);
        
        // 判断是否访问
        if (!redisService.hasSetValue(RedisConstant.UNIQUE_VISITOR, md5)) {
            log.info("新的访问用户，标识: {}", md5);
            
            // 访问量+1
            redisService.incr(RedisConstant.BLOG_VIEW_COUNT, 1);
            log.info("博客访问量+1");
            
            // 保存唯一标识
            redisService.setSet(RedisConstant.UNIQUE_VISITOR, md5);
            log.info("已将用户标识存入 Redis，标识: {}", md5);
        } else {
            log.info("用户 {} 已访问过，跳过记录", md5);
        }
    }
    
    
    /**
     * 查看博客信息
     *
     * @return BlogInfoResp
     */
    @Override
    public BlogInfoResp getBlogInfo() {

        // 获取文章数量
        Long articleCount = articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                .eq(Article::getStatus, ArticleStatusEnum.PUBLIC.getStatus())
                .eq(Article::getIsDelete, CommonConstant.FALSE));
        log.info("文章数量: {}", articleCount);
        
        // 获取分类数量
        Long categoryCount = categoryMapper.selectCount(null);
        log.info("分类数量: {}", categoryCount);
        
        // 获取标签数量
        Long tagCount = tagMapper.selectCount(null);
        log.info("标签数量: {}", tagCount);
        
        // 获取博客访问量
        Integer count = redisService.getObject(RedisConstant.BLOG_VIEW_COUNT);
        String viewCount = Optional.ofNullable(count).orElse(0).toString();
        log.info("博客访问量: {}", viewCount);
        
        // 获取网站配置
        SiteConfig siteConfig = siteConfigService.getSiteConfig();
        log.debug("网站配置已获取");
        
        BlogInfoResp response = BlogInfoResp.builder()
                .articleCount(articleCount)
                .categoryCount(categoryCount)
                .tagCount(tagCount)
                .viewCount(viewCount)
                .siteConfig(siteConfig)
                .build();

        
        return response;
    }
    
    
    /**
     * 查看个人后台信息
     * @return
     */
    //TODO 查看博客后台信息
    @Override
    public BlogBackInfoResp getBlogBackInfo() {
        // 访问量
        Integer viewCount = redisService.getObject(RedisConstant.BLOG_VIEW_COUNT);
        log.info("获取到访问量: {}", viewCount);
        
        // 留言量
        Long messageCount = messageMapper.selectCount(null);
        log.info("获取到留言量: {}", messageCount);
        
        // 用户量
        Long userCount = userMapper.selectCount(null);
        log.info("获取到用户量: {}", userCount);
        
        // 文章量
        Long articleCount = articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                .eq(Article::getIsDelete, CommonConstant.FALSE));
        log.info("获取到文章量: {}", articleCount);
        
        // 分类数据
        List<CategoryResp> categoryRespList = categoryMapper.selectCategoryVO();
        log.info("获取到分类数据, 数量: {}", categoryRespList.size());
        
        // 标签数据
        List<TagOptionResp> tagVOList = tagMapper.selectTagOptionList();
        log.info("获取到标签数据, 数量: {}", tagVOList.size());
        
        // 查询用户浏览
        DateTime startTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
        DateTime endTime = DateUtil.endOfDay(new Date());
        List<UserViewResp> userViewRespList = visitLogMapper.selectUserViewList(startTime, endTime);
        log.info("获取到用户浏览数据, 数量: {}", userViewRespList.size());
        
        // 文章统计
        List<ArticleStatisticsResp> articleStatisticsList = articleMapper.selectArticleStatistics();
        log.info("获取到文章统计数据, 数量: {}", articleStatisticsList.size());
        
        // 查询redis访问量前五的文章
        Map<Object, Double> articleMap = redisService.zReverseRangeWithScore(RedisConstant.ARTICLE_VIEW_COUNT, 0, 4);
        log.info("获取到redis访问量前五的文章: {}", articleMap);
        
        BlogBackInfoResp blogBackInfoResp = BlogBackInfoResp.builder()
                .articleStatisticsList(articleStatisticsList)
                .tagVOList(tagVOList)
                .viewCount(viewCount)
                .messageCount(messageCount)
                .userCount(userCount)
                .articleCount(articleCount)
                .categoryVOList(categoryRespList)
                .userViewVOList(userViewRespList)
                .build();
        
        if (CollectionUtils.isNotEmpty(articleMap)) {
            // 查询文章排行
            List<ArticleRankResp> articleRankRespList = articleService.listArticleRank(articleMap);
            blogBackInfoResp.setArticleRankVOList(articleRankRespList);
            log.info("获取到文章排行数据, 数量: {}", articleRankRespList.size());
        }

        return blogBackInfoResp;
    }
    
    /**
     * 查看我的个人信息
     * @return
     */
    public String getAbout() {
        SiteConfig siteConfig = redisService.getObject(RedisConstant.SITE_SETTING);
        return siteConfig.getAboutMe();
    }
    
    
    
    
    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        return false;
    }
    
    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return false;
    }
    
    @Override
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        return false;
    }
    
    @Override
    public boolean saveOrUpdate(T entity) {
        return false;
    }
    
    @Override
    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        return null;
    }
    
    @Override
    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return Collections.emptyMap();
    }
    
    @Override
    public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }
    
    @Override
    public BaseMapper<T> getBaseMapper() {
        return null;
    }
    
    @Override
    public Class<T> getEntityClass() {
        return null;
    }
}
