package com.wy.wydemo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.wy.wydemo.annotation.OptLogger;
import com.wy.wydemo.constant.OptTypeConstant;
import com.wy.wydemo.model.vo.query.CarouselQuery;
import com.wy.wydemo.model.vo.request.CarouselReqVo;
import com.wy.wydemo.model.vo.response.CarouselBackResp;
import com.wy.wydemo.model.vo.response.CarouselResp;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.CarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description: 轮播图控制器
 * @class: CarouselController
 * @author: yu_wei
 * @create: 2024/11/07 20:03
 */
@Api(tags = "轮流图管理")
@RestController
public class CarouselController {
    
    
    @Autowired
    private CarouselService carouselService;
    
    
    /**
     * 查看轮播图列表
     *
     * @return 轮播图列表
     */
    @ApiOperation(value = "查看轮播图列表")
    @GetMapping("/carousel/list")
    public Result<List<CarouselResp>> getCarouselList() {
        return Result.success(carouselService.getCarouselList());
    }
    
    /**
     * 分页查看轮播图列表
     *
     * @param carouselQuery 查询条件
     * @return {@link PageResult< CarouselBackResp >} 轮播图列表
     */
    @ApiOperation(value = "分页查看轮播图列表")
//    @SaCheckPermission("web:carousel:list")
    @GetMapping("/admin/carousel/list")
    public Result<PageResult<CarouselBackResp>> getCarouselVOList(CarouselQuery carouselQuery) {
        return Result.success(carouselService.getCarouselVOList(carouselQuery));
    }
    
    
    
    
    /**
     * 上传轮播图片到mysql
     *
     * @param file 文件
     * @return {@link Result<String>} 图片地址
     */
    //TODO 上传图片
    @OptLogger(value = OptTypeConstant.UPLOAD)
    @ApiOperation(value = "上传轮播图片")
    @ApiImplicitParam(name = "file", value = "轮播图片", required = true, dataType = "MultipartFile")
//    @SaCheckPermission("web:carousel:upload")
    @PostMapping("/admin/carousel/upload")
    public Result<String> uploadCarousel(@RequestParam("file") MultipartFile file) {
        return Result.success(carouselService.uploadCarousel(file));
    }
    
    
    /**
     * 添加轮播图
     *
     * @param carouselReqVo 轮播图信息
     * @return {@link Result<>}
     */
    @OptLogger(value = OptTypeConstant.ADD)
    @ApiOperation(value = "添加轮播图")
//    @SaCheckPermission("web:carousel:add")
    @PostMapping("/admin/carousel/add")
    public Result<?> addCarousel(@Validated @RequestBody CarouselReqVo carouselReqVo) {
        carouselService.addCarousel(carouselReqVo);
        return Result.success();
    }
    
    
}
