package com.wy.wydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.mapper.CarouselMapper;
import com.wy.wydemo.model.entity.Carousel;
import com.wy.wydemo.model.enums.FilePathEnum;
import com.wy.wydemo.model.vo.query.CarouselQuery;
import com.wy.wydemo.model.vo.request.CarouselReqVo;
import com.wy.wydemo.model.vo.response.CarouselBackResp;
import com.wy.wydemo.model.vo.response.CarouselResp;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.service.BlogFileService;
import com.wy.wydemo.service.CarouselService;
import com.wy.wydemo.strategy.context.UploadStrategyContext;
import com.wy.wydemo.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @class: CarouselServiceImpl
 * @author: yu_wei
 * @create: 2024/11/07 20:04
 */
@Service
@Slf4j
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {
    
    
    @Autowired
    private CarouselMapper carouselMapper;
    
    @Autowired
    private UploadStrategyContext uploadStrategyContext;
    
    @Autowired
    private BlogFileService blogFileService;
    
    /**
     * 查询轮播图列表
     * @return
     */
    public List<CarouselResp> getCarouselList() {
        return carouselMapper.selectCarouselList();
    }
    
    
    /**
     * 查询轮播图列表分页查询轮播图
     * @param carouselQuery
     * @return
     */
    @Override
    public PageResult<CarouselBackResp> getCarouselVOList(CarouselQuery carouselQuery) {
        // 查询轮播图数量
        Long count = carouselMapper.selectCount(null);
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询轮播图列表
        List<CarouselBackResp> carouselList = carouselMapper.selectBackCarouselList(carouselQuery);
        return new PageResult<>(carouselList, count);
    }
    
    
    /**
     * 上传轮播图片
     * @param file
     * @return
     */
    @Override
    public String uploadCarousel(MultipartFile file) {
        try {
            log.info("开始上传轮播图文件: {}", file.getOriginalFilename());
            
            // 上传文件
            String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.CAROUSEL.getPath());
            log.info("文件上传成功，URL: {}", url);
            
            // 保存文件信息
            blogFileService.saveBlogFile(file, url, FilePathEnum.CAROUSEL.getFilePath());
            log.info("文件信息保存成功");
            
            return url;
        } catch (Exception e) {
            log.info("上传轮播图文件失败: {}", file.getOriginalFilename(), e);
            throw new RuntimeException("上传轮播图文件失败", e);
        }
    }
    
    
    /**
     * 添加轮播图
     * @param carouselReqVo
     */
    
    @Override
    public void addCarousel(CarouselReqVo carouselReqVo) {
        try {
            log.info("开始添加轮播图: {}", carouselReqVo);
            
            // 将请求对象转换为实体对象
            Carousel carousel = BeanCopyUtils.copyBean(carouselReqVo, Carousel.class);
            log.info("轮播图对象转换成功: {}", carousel);
            
            // 插入轮播图数据
            carouselMapper.insert(carousel);
            log.info("轮播图数据插入成功: {}", carousel);
        } catch (Exception e) {
            log.error("添加轮播图失败: {}", carouselReqVo, e);
            throw new RuntimeException("添加轮播图失败", e);
        }
    }
}
