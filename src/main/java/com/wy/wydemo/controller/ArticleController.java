package com.wy.wydemo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.wy.wydemo.annotation.OptLogger;
import com.wy.wydemo.annotation.VisitLogger;
import com.wy.wydemo.model.enums.LikeTypeEnum;
import com.wy.wydemo.model.vo.query.ArticleQuery;
import com.wy.wydemo.model.vo.query.PageQuery;
import com.wy.wydemo.model.vo.request.ArticleReq;
import com.wy.wydemo.model.vo.request.DeleteReq;
import com.wy.wydemo.model.vo.request.RecommendReq;
import com.wy.wydemo.model.vo.request.TopReq;
import com.wy.wydemo.model.vo.response.*;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.ArticleService;
import com.wy.wydemo.strategy.context.LikeStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.wy.wydemo.constant.OptTypeConstant.*;

/**
 * @description: 文章帖子管理
 * @class: ArticleController
 * @author: yu_wei
 * @create: 2024/11/05 10:20
 */
@Api(tags = "文章帖子管理")
@RestController
@Slf4j
public class ArticleController {
    
    
    @Autowired
    private ArticleService articleService;
    
    
    @Autowired
    private LikeStrategyContext likeStrategyContext;
    
    /**
     * 查看后台文章列表
     *
     * @param articleQuery 文章查询条件
     * @return {@link Result<   ArticleBackResp   >} 后台文章列表
     */
    @ApiOperation(value = "查询后台文章列表")
    //    @SaCheckPermission("blog:article:list")
    @GetMapping("/admin/article/list")
    public Result<com.wy.wydemo.model.vo.response.PageResult<ArticleBackResp>> listArticleBackVO(ArticleQuery articleQuery) {
        return Result.success(articleService.listArticleBackVO(articleQuery));
    }
    
    /**
     * 添加文章
     *
     * @param article 文章信息
     * @return {@link Result<>}
     */
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加文章")
    //    @SaCheckPermission("blog:article:add")
    @PostMapping("/admin/article/add")
    public Result<?> addArticle(@Validated @RequestBody ArticleReq article) {
        articleService.addArticle(article);
        return Result.success();
    }
    
    
    /**
     * 删除文章
     *
     * @param articleIdList 文章id集合
     * @return {@link Result<>}
     */
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除文章")
    //    @SaCheckPermission("blog:article:delete")
    @DeleteMapping("/admin/article/delete")
    public Result<?> deleteArticle(@RequestBody List<Integer> articleIdList) {
        articleService.deleteArticle(articleIdList);
        return Result.success();
    }
    
    
    /**
     * 回收或恢复文章
     *
     * @param delete 逻辑删除
     * @return {@link Result<>}
     */
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "回收或恢复文章")
    //    @SaCheckPermission("blog:article:recycle")
    @PutMapping("/admin/article/recycle")
    public Result<?> updateArticleDelete(@Validated @RequestBody DeleteReq delete) {
        articleService.updateArticleDelete(delete);
        return Result.success();
    }
    
    
    /**
     * 修改文章
     *
     * @param article 文章信息
     * @return {@link Result<>}
     */
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改文章")
    //    @SaCheckPermission("blog:article:update")
    @PutMapping("/admin/article/update")
    //TODO 应该新建一个修改的请求体
    public Result<?> updateArticle(@Validated @RequestBody ArticleReq article) {
        articleService.updateArticle(article);
        return Result.success();
    }
    
    
    /**
     * 根据ID查看文章
     *
     * @param articleId 文章id
     * @return {@link Result<  ArticleInfoResp  >} 后台文章
     */
    @ApiOperation(value = "编辑文章")
    //    @SaCheckPermission("blog:article:edit")
    @GetMapping("/admin/article/edit/{articleId}")
    public Result<ArticleInfoResp> editArticle(@PathVariable("articleId") Integer articleId) {
        return Result.success(articleService.editArticle(articleId));
    }
    
    
    /**
     * 上传文章图片/封面
     *
     * @param multipartFile 文件
     * @return {@link Result<String>} 文章图片地址
     */
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传图片/文件")
    @ApiImplicitParam(name = "file", value = "文件", required = true, dataTypeClass = MultipartFile.class)
    //    @SaCheckPermission("blog:article:upload")
    @PostMapping("/admin/article/upload")
    public Result<String> saveArticleImages(@RequestPart("file") MultipartFile multipartFile) {
        return Result.success(articleService.saveArticleImages(multipartFile));
    }
    
    
    /**
     * 置顶文章
     *
     * @param top 置顶信息
     * @return {@link Result<>}
     */
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "置顶文章")
    //    @SaCheckPermission("blog:article:top")
    @PutMapping("/admin/article/top")
    public Result<?> updateArticleTop(@Validated @RequestBody TopReq top) {
        articleService.updateArticleTop(top);
        return Result.success();
    }
    
    
    /**
     * 推荐文章
     *
     * @param recommend 推荐信息
     * @return {@link Result<>}
     */
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "推荐文章")
    //    @SaCheckPermission("blog:article:recommend")
    @PutMapping("/admin/article/recommend")
    public Result<?> updateArticleRecommend(@Validated @RequestBody RecommendReq recommend) {
        articleService.updateArticleRecommend(recommend);
        return Result.success();
    }
    
    
    /**
     * 点赞文章
     *
     * @param articleId 文章id
     * @return {@link Result<>}
     */
    @SaCheckLogin
    @ApiOperation(value = "点赞文章")
    @PostMapping("/article/{articleId}/like")
    public Result<?> likeArticle(@PathVariable("articleId") Integer articleId) {
        likeStrategyContext.executeLikeStrategy(LikeTypeEnum.ARTICLE, articleId);
        return Result.success();
    }
    
    
    /**
     * 搜索文章
     *
     * @param keyword 关键字
     * @return {@link Result<  ArticleSearchResp  >} 文章列表
     */
    @ApiOperation(value = "搜索文章")
    @GetMapping("/article/search")
    public Result<List<ArticleSearchResp>> listArticlesBySearch(String keyword) {
        return Result.success(articleService.listArticlesBySearch(keyword));
    }
    
    /**
     * 查看首页文章列表
     *
     * @param pageQuery 分页条件
     * @return {@link Result<  ArticleHomeResp  >}
     */
    @VisitLogger(value = "首页")
    @ApiOperation(value = "查看首页文章列表")
    @GetMapping("/article/list")
    public Result<com.wy.wydemo.model.vo.response.PageResult<ArticleHomeResp>> listArticleHomeVO(PageQuery pageQuery) {
        return Result.success(articleService.listArticleHomeVO(pageQuery));
    }
    
    
    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    @ApiOperation(value = "分页查询文章列表")
    @GetMapping("/article/paginated-list")
    public Result<PageResult<ArticleHomeResp>> listArticlesWithPagination(PageQuery pageQuery) {
        return Result.success(articleService.listArticlesWithPagination(pageQuery));
    }


    
    
    
    /**
     * 查看文章
     *
     * @param articleId 文章id
     * @return {@link Result<ArticleResp>} 首页文章
     */
    @VisitLogger(value = "文章")
    @ApiOperation(value = "查看文章具体信息")
    @GetMapping("/article/{articleId}")
    public Result<ArticleResp> getArticleHomeById(@PathVariable("articleId") Integer articleId) {
        return Result.success(articleService.getArticleHomeById(articleId));
    }
    
    
    /**
     * 查看推荐文章
     *
     * @return {@link Result< ArticleRecommendResp >} 推荐文章
     */
    @ApiOperation(value = "查看推荐文章")
    @GetMapping("/article/recommend")
    public Result<List<ArticleRecommendResp>> listArticleRecommendVO() {
        return Result.success(articleService.listArticleRecommendVO());
    }
    
    
    /**
     * 查看文章归档
     *
     * @param pageQuery 分页条件
     * @return {@link Result< ArchiveResp >} 文章归档列表
     */
    @VisitLogger(value = "归档")
    @ApiOperation(value = "查看文章归档")
    @GetMapping("/archives/list")
    public Result<PageResult<ArchiveResp>> listArchiveVO(PageQuery pageQuery) {
        return Result.success(articleService.listArchiveVO(pageQuery));
    }
    
    
    
    /**
     * 取消点赞文章
     *
     * @param articleId 文章id
     * @return {@link Result<>}
     */
    @SaCheckLogin
    @ApiOperation(value = "取消点赞文章")
    @PostMapping("/article/{articleId}/unlike")
    public Result<?> unlikeArticle(@PathVariable("articleId") Integer articleId) {
        likeStrategyContext.executeUnlikeStrategy(LikeTypeEnum.ARTICLE, articleId);
        return Result.success();
    }
    
    
    
}
