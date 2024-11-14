package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Category;
import com.wy.wydemo.model.vo.query.CategoryQuery;
import com.wy.wydemo.model.vo.response.CategoryBackResp;
import com.wy.wydemo.model.vo.response.CategoryResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:分类 Mapper
 * @class: CategoryMapper
 * @author: yu_wei
 * @create: 2024/11/05 10:50
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    
    
    /**
     * 查询分类列表
     *
     * @return 分类列表
     */
    List<CategoryResp> selectCategoryVO();
    
    
    /**
     * 分页查询后台分类列表
     * @param categoryQuery
     * @return
     */
    List<CategoryBackResp> selectBackCategoryList(CategoryQuery categoryQuery);
}