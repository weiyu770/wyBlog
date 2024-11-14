package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Carousel;
import com.wy.wydemo.model.vo.query.CarouselQuery;
import com.wy.wydemo.model.vo.response.CarouselBackResp;
import com.wy.wydemo.model.vo.response.CarouselResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @class: CarouselMapper
 * @author: yu_wei
 * @create: 2024/11/07 20:05
 */
@Mapper
public interface CarouselMapper extends BaseMapper<Carousel> {
    
    
    /**
     * 查询轮播图列表
     * @return
     */
    List<CarouselResp> selectCarouselList();
    
    /**
     * 分页查询轮播图列表
     * @param carouselQuery
     * @return
     */
    List<CarouselBackResp> selectBackCarouselList(CarouselQuery carouselQuery);
}
