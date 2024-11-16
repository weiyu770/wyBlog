package com.wy.wydemo.controller;

import com.wy.wydemo.annotation.OptLogger;
import com.wy.wydemo.annotation.VisitLogger;
import com.wy.wydemo.model.vo.query.ArticleConditionQuery;
import com.wy.wydemo.model.vo.query.CategoryQuery;
import com.wy.wydemo.model.vo.request.CategoryReq;
import com.wy.wydemo.model.vo.response.*;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wy.wydemo.constant.OptTypeConstant.*;

/**
 * @description: 分类控制器
 * @class: CategoryController
 * @author: yu_wei
 * @create: 2024/11/07 20:57
 */
//比如说用Java python
@Api(tags = "分型管理")
@RestController
public class CategoryController {
    
    
    @Autowired
    private CategoryService categoryService;
    
    
    /**
     * 查看后台分类列表
     *
     * @param categoryQuery 分类查询条件
     * @return {@link CategoryBackResp} 后台分类
     */
    @ApiOperation(value = "分页查看后台分类列表")
    //    @SaCheckPermission("blog:category:list")
    @GetMapping("/admin/category/list")
    public Result<PageResult<CategoryBackResp>> listCategoryBackVO(CategoryQuery categoryQuery) {
        return Result.success(categoryService.listCategoryBackVO(categoryQuery));
    }
    
    /**
     * 添加分类
     *
     * @param category 分类信息
     * @return {@link Result<>}
     */
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加分类")
    //    @SaCheckPermission("blog:category:add")
    @PostMapping("/admin/category/add")
    public Result<?> addCategory(@Validated @RequestBody CategoryReq category) {
        categoryService.addCategory(category);
        return Result.success();
    }
    
    
    /**
     * 删除分类 / 批量删除
     *
     * @param categoryIdList 分类id集合
     * @return {@link Result<>}
     */
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除多个")
    //    @SaCheckPermission("blog:category:delete")
    @DeleteMapping("/admin/category/delete")
    public Result<?> deleteCategory(@RequestBody List<Integer> categoryIdList) {
        categoryService.deleteCategory(categoryIdList);
        return Result.success();
    }
    
    /**
     * 删除单个分类
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个")
    @DeleteMapping("/deleteCategory/{id}")
    public Result<?> deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
        return Result.success();
    }
    
    /**
     * 修改分类
     *
     * @param category 分类信息
     * @return {@link Result<>}
     */
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改分类")
    //    @SaCheckPermission("blog:category:update")
    @PutMapping("/admin/category/update")
    public Result<?> updateCategory(@Validated @RequestBody CategoryReq category) {
        categoryService.updateCategory(category);
        return Result.success();
    }
    
    
    /**
     * 查看分类选项
     *
     * @return {@link Result<   CategoryOptionResp   >} 分类列表
     */
    @ApiOperation(value = "查看分类选项")
    @GetMapping("/admin/category/option")
    public Result<List<CategoryOptionResp>> listCategoryOption() {
        return Result.success(categoryService.listCategoryOption());
    }
    
    /**
     * 查看分类列表
     *
     * @return {@link Result< CategoryReq >} 分类列表
     */
    @VisitLogger(value = "文章分类")
    @ApiOperation(value = "查看分类列表")
    @GetMapping("/category/list")
    public Result<List<CategoryResp>> listCategoryVO() {
        return Result.success(categoryService.listCategoryVO());
    }
    
    /**
     * 查看分类下的文章
     *
     * @param articleConditionQuery 查询条件
     * @return 文章列表
     */
    @VisitLogger(value = "分类文章")
    @ApiOperation(value = "查看分类下的文章")
    @GetMapping("/category/article")
    public Result<ArticleConditionList> listArticleCategory(@Validated ArticleConditionQuery articleConditionQuery) {
        return Result.success(categoryService.listArticleCategory(articleConditionQuery));
    }
    
}
