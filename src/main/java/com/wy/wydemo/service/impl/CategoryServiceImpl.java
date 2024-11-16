package com.wy.wydemo.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.mapper.ArticleMapper;
import com.wy.wydemo.mapper.CategoryMapper;
import com.wy.wydemo.mapper.SiteConfigMapper;
import com.wy.wydemo.model.entity.Article;
import com.wy.wydemo.model.entity.Category;
import com.wy.wydemo.model.entity.SiteConfig;
import com.wy.wydemo.model.vo.query.ArticleConditionQuery;
import com.wy.wydemo.model.vo.query.CategoryQuery;
import com.wy.wydemo.model.vo.request.CategoryReq;
import com.wy.wydemo.model.vo.response.*;
import com.wy.wydemo.service.CategoryService;
import com.wy.wydemo.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @class: CategoryServiceImpl
 * @author: yu_wei
 * @create: 2024/11/07 20:58
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Autowired
    private ArticleMapper articleMapper;
    
    private static final int maxDeep = 3;
    
    
    @Override
    public PageResult<CategoryBackResp> listCategoryBackVO(CategoryQuery categoryQuery) {
        // 查询分类数量
        log.info("开始查询分类数量，查询条件：{}", categoryQuery);
        
        Long count = categoryMapper.selectCount(new LambdaQueryWrapper<Category>()
                .like(StringUtils.hasText(categoryQuery.getKeyword()), Category::getCategoryName,
                        categoryQuery.getKeyword()));
        log.info("分类数量查询结果：{}", count);
        
        if (count == 0) {
            log.info("未找到符合条件的分类，返回空结果");
            return new PageResult<>();
        }
        
        // 分页查询分类列表
        log.info("开始分页查询分类列表，分页参数：{}", categoryQuery);
        List<CategoryBackResp> categoryList = categoryMapper.selectBackCategoryList(categoryQuery);
        log.info("分类列表查询结果：{}", categoryList);
        
        // 当前分类id列表
        Set<Integer> categoryIdList = categoryList.stream()
                .map(CategoryBackResp::getId)
                .collect(Collectors.toSet());
        log.info("当前分类id列表：{}", categoryIdList);
        
        // 递归查询父级分类
        List<CategoryBackResp> res = categoryList.stream()
                .map(category -> {
                    Integer parentId = category.getParentId();
                    // parentId不在当前分类id列表，说明为父级分类id，根据此id作为递归的开始条件节点
                    if (!categoryIdList.contains(parentId)) {
                        categoryIdList.add(parentId);
                        log.info("发现父级分类id：{}，开始递归处理", parentId);
                        return recurCategoryList(categoryList, parentId, 0, maxDeep);
                    }
                    return new ArrayList<CategoryBackResp>();
                }).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        
        // 执行分页
        int fromIndex = categoryQuery.getCurrent();
        int size = categoryQuery.getSize();
        int toIndex = categoryList.size() - fromIndex > size ? fromIndex + size : res.size();
        log.info("执行分页，fromIndex: {}, size: {}, toIndex: {}", fromIndex, size, toIndex);
        
        PageResult<CategoryBackResp> result = new PageResult<>(res.subList(fromIndex, toIndex), (long) res.size());
        log.info("分页查询结果：{}", result);
        
        return result;
    }
    
    
    /**
     * 添加分类
     * @param category
     */
    @Override
    public void addCategory(CategoryReq category) {
        // 分类是否存在
        Category existCategory = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, category.getCategoryName()));
        Assert.isNull(existCategory, category.getCategoryName() + "分类已存在");
        // 添加新分类
        Category newCategory = Category.builder()
                .categoryName(category.getCategoryName())
                .build();
        baseMapper.insert(newCategory);
    }
    
    
    /**
     * 删除分类
     * @param categoryIdList
     */
    @Override
    public void deleteCategory(List<Integer> categoryIdList) {
        // 分类下是否有文章
        Long count = articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                .in(Article::getCategoryId, categoryIdList));
        Assert.isFalse(count > 0, "删除失败，分类下存在文章");
        // 批量删除分类
        categoryMapper.deleteBatchIds(categoryIdList);
    }
    
    /**
     * 更新分类
     * @param category
     */
    @Override
    public void updateCategory(CategoryReq category) {
        log.info("开始更新分类，请求参数：{}", category);
        
        // 检查分类是否存在
        Category existCategory = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, category.getCategoryName()));
        
        log.debug("查询分类结果：{}", existCategory);
        
        // 如果分类已存在且不是当前分类，抛出异常
        if (ObjectUtils.isNotEmpty(existCategory) && !existCategory.getId().equals(category.getId())) {
            log.warn("分类已存在，分类名称：{}", category.getCategoryName());
            throw new IllegalArgumentException(category.getCategoryName() + "分类已存在");
        }
        
        // 构建新分类对象
        Category newCategory = Category.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
        
        log.debug("构建的新分类对象：{}", newCategory);
        
        // 更新分类
        int updateResult = categoryMapper.updateById(newCategory);
        
        log.info("分类更新结果：{}，更新记录数：{}", newCategory, updateResult);
        
        if (updateResult <= 0) {
            log.error("分类更新失败，分类ID：{}", category.getId());
            throw new RuntimeException("分类更新失败");
        }
        
        log.info("分类更新成功，分类ID：{}", category.getId());
    }
    
    /**
     * 查看分类选项
     * @return
     */
    @Override
    public List<CategoryOptionResp> listCategoryOption() {
        // 查询分类
        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .orderByDesc(Category::getId));
        return BeanCopyUtils.copyBeanList(categoryList, CategoryOptionResp.class);
    }
    
    
    /**
     * 查看分类列表
     * @return
     */
    @Override
    public List<CategoryResp> listCategoryVO() {
        return categoryMapper.selectCategoryVO();
    }
    
    /**
     * 查看分类下的文章
     * @param articleConditionQuery
     * @return
     */
    @Override
    public ArticleConditionList listArticleCategory(ArticleConditionQuery articleConditionQuery) {
        // 开始执行方法时记录日志
        log.info("开始查看分类下的文章，查询条件：{}", articleConditionQuery);
        
        // 查询文章列表
        List<ArticleConditionResp> articleConditionList = articleMapper.selectArticleListByCondition(articleConditionQuery);
        log.debug("查询到的文章列表：{}", articleConditionList);
        
        // 查询分类名称
        String name = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                        .select(Category::getCategoryName)
                        .eq(Category::getId, articleConditionQuery.getCategoryId()))
                .getCategoryName();
        log.debug("查询到的分类名称：{}", name);
        
        // 构建返回结果并记录日志
        ArticleConditionList result = ArticleConditionList.builder()
                .articleConditionVOList(articleConditionList)
                .name(name)
                .build();
        log.info("返回分类下的文章列表：{}", result);
        
        return result;
    }
    
    /**
     * 删除单个分类 / 根据ID删除分类
     * @param id
     */
    @Override
    public void deleteCategoryById(Integer id) {
        categoryMapper.deleteById(id);
    }
    
    
    /**
     * 递归生成分类列表
     *
     * @param parentId     父分类id
     * @param categoryList 分类列表
     * @return 分类列表
     */
    private List<CategoryBackResp> recurCategoryList(List<CategoryBackResp> categoryList, Integer parentId, int currentDeep, int maxDeep) {
        List<CategoryBackResp> tree = new ArrayList<>();
        if (maxDeep < 0) {
            return tree;
        }
        if (currentDeep == maxDeep) {
            return tree;
        } else {
            for (CategoryBackResp category : categoryList) {
                if (category.getParentId().equals(parentId)) {
                    category.setChildren(recurCategoryList(categoryList, category.getId(), currentDeep + 1, maxDeep));
                    tree.add(category);
                }
            }
        }
        return tree;
    }
    
}
