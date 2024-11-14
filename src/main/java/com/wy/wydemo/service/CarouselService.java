package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Carousel;
import com.wy.wydemo.model.vo.query.CarouselQuery;
import com.wy.wydemo.model.vo.request.CarouselReqVo;
import com.wy.wydemo.model.vo.response.CarouselBackResp;
import com.wy.wydemo.model.vo.response.CarouselResp;
import com.wy.wydemo.model.vo.response.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description: 轮播图服务接口
 * @class: CarouselService
 * @author: yu_wei
 * @create: 2024/11/07 20:03
 */
public interface CarouselService extends IService<Carousel> {
    
    /**
     * 查看轮播图列表
     */
    List<CarouselResp> getCarouselList();
    
    
    /**
     * 分页查看轮播图列表
     * @param carouselQuery
     * @return
     */
    PageResult<CarouselBackResp> getCarouselVOList(CarouselQuery carouselQuery);
    
    
    /**
     * 上传轮播图图片
     * @param file
     * @return
     */
    String uploadCarousel(MultipartFile file);
    
    
    /**
     * 添加轮播图
     * @param carouselReqVo
     */
    void addCarousel(CarouselReqVo carouselReqVo);
    
}
   