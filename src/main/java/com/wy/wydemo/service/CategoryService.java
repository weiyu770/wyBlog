package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Category;
import com.wy.wydemo.model.vo.query.ArticleConditionQuery;
import com.wy.wydemo.model.vo.query.CategoryQuery;
import com.wy.wydemo.model.vo.request.CategoryReq;
import com.wy.wydemo.model.vo.response.*;

import java.util.List;

/**
 * @description:
 * @class: CategoryService
 * @author: yu_wei
 * @create: 2024/11/07 20:57
 */
public interface CategoryService extends IService<Category> {
    
    
    /**
     * 查看后台分类列表
     * @param categoryQuery
     * @return
     */
    PageResult<CategoryBackResp> listCategoryBackVO(CategoryQuery categoryQuery);
    
    
    /**
     * 添加分类
     * @param category
     */
    void addCategory(CategoryReq category);
    
    
    /**
     * 删除多个分类 / 批量删除
     * @param categoryIdList
     */
    void deleteCategory(List<Integer> categoryIdList);
    
    /**
     * 修改分类
     * @param category
     */
    void updateCategory(CategoryReq category);
    
    
    /**
     * 查看分类选项
     * @return
     */
    List<CategoryOptionResp> listCategoryOption();
    
    
    /**
     * 查看分类列表
     * @return
     */
    List<CategoryResp> listCategoryVO();
    
    /**
     * 查看分类下的文章
     * @param articleConditionQuery
     * @return
     */
    ArticleConditionList listArticleCategory(ArticleConditionQuery articleConditionQuery);
    
    /**
     * 删除单个分类
     * @param id
     */
    void deleteCategoryById(Integer id);
}
