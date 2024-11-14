package com.wy.wydemo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.wy.wydemo.annotation.AccessLimit;
import com.wy.wydemo.annotation.OptLogger;
import com.wy.wydemo.annotation.VisitLogger;
import com.wy.wydemo.model.enums.LikeTypeEnum;
import com.wy.wydemo.model.vo.query.PageQuery;
import com.wy.wydemo.model.vo.query.TalkQuery;
import com.wy.wydemo.model.vo.request.TalkReq;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.model.vo.response.TalkBackInfoResp;
import com.wy.wydemo.model.vo.response.TalkBackResp;
import com.wy.wydemo.model.vo.response.TalkResp;
import com.wy.wydemo.result.Result;
import com.wy.wydemo.service.TalkService;
import com.wy.wydemo.strategy.context.LikeStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.wy.wydemo.constant.OptTypeConstant.*;

/**
 * @description: 说说管理
 * @class: TalkController
 * @author: yu_wei
 * @create: 2024/11/08 17:19
 */
@Api(tags = "说说管理")
@RestController
public class TalkController {
    
    @Autowired
    private TalkService talkService;
    
    @Autowired
    private LikeStrategyContext likeStrategyContext;
    
    /**
     * 查看后台说说列表
     *
     * @param talkQuery 说说查询条件
     * @return {@link TalkBackResp} 后台说说
     */
    @ApiOperation(value = "分页查询后台说说列表")
//    @SaCheckPermission("web:talk:list")
    @GetMapping("/admin/talk/list")
    public Result<PageResult<TalkBackResp>> listTalkBackVO(TalkQuery talkQuery) {
        return Result.success(talkService.listTalkBackVO(talkQuery));
    }
    
    /**
     * 上传说说图片
     *
     * @param file 文件
     * @return {@link Result<String>}
     */
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传说说图片")
    @ApiImplicitParam(name = "file", value = "相册封面", required = true, dataType = "MultipartFile")
//    @SaCheckPermission("web:talk:upload")
    @PostMapping("/admin/talk/upload")
    public Result<String> uploadTalkCover(@RequestParam("file") MultipartFile file) {
        return Result.success(talkService.uploadTalkCover(file));
    }
    
    /**
     * 添加说说
     *
     * @param talk 说说信息
     * @return {@link Result<>}
     */
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加说说")
//    @SaCheckPermission("web:talk:add")
    @PostMapping("/admin/talk/add")
    public Result<?> addTalk(@Validated @RequestBody TalkReq talk) {
        talkService.addTalk(talk);
        return Result.success();
    }
    
    /**
     * 删除说说
     *
     * @param talkId 说说id
     * @return {@link Result<>}
     */
    @OptLogger(value = DELETE)
    @ApiOperation(value = "删除说说")
//    @SaCheckPermission("web:talk:delete")
    @DeleteMapping("/admin/talk/delete/{talkId}")
    public Result<?> deleteTalk(@PathVariable("talkId") Integer talkId) {
        talkService.deleteTalk(talkId);
        return Result.success();
    }
    
    /**
     * 修改说说
     *
     * @param talk 说说信息
     * @return {@link Result<>}
     */
    @OptLogger(value = UPDATE)
    @ApiOperation(value = "修改说说")
//    @SaCheckPermission("web:talk:update")
    @PutMapping("/admin/talk/update")
    public Result<?> updateTalk(@Validated @RequestBody TalkReq talk) {
        talkService.updateTalk(talk);
        return Result.success();
    }
    
    /**
     * 编辑说说
     *
     * @param talkId 说说id
     * @return {@link TalkBackResp} 后台说说
     */
    @ApiOperation(value = "根据ID编辑说说")
//    @SaCheckPermission("web:talk:edit")
    @GetMapping("/admin/talk/edit/{talkId}")
    public Result<TalkBackInfoResp> editTalk(@PathVariable("talkId") Integer talkId) {
        return Result.success(talkService.editTalk(talkId));
    }
    
    /**
     * 点赞说说
     *
     * @param talkId 说说id
     * @return {@link Result<>}
     */
    @SaCheckLogin
    @ApiOperation(value = "点赞说说")
    @AccessLimit(seconds = 60, maxCount = 3)
//    @SaCheckPermission("web:talk:like")
    @PostMapping("/talk/{talkId}/like")
    public Result<?> saveTalkLike(@PathVariable("talkId") Integer talkId) {
        likeStrategyContext.executeLikeStrategy(LikeTypeEnum.TALK, talkId);
        return Result.success();
    }
    
    /**
     * 查看首页说说
     *
     * @return {@link Result<String>}
     */
    @ApiOperation(value = "查看首页说说")
    @GetMapping("/home/talk")
    public Result<List<String>> listTalkHome() {
        return Result.success(talkService.listTalkHome());
    }
    
    /**
     * 查看说说列表
     *
     * @return {@link Result< TalkResp >}
     */
    @VisitLogger(value = "说说列表")
    @ApiOperation(value = "查看说说列表")
    @GetMapping("/talk/list")
    public Result<PageResult<TalkResp>> listTalkVO(@Validated PageQuery pageQuery) {
        return Result.success(talkService.listTalkVO(pageQuery));
    }
    
    /**
     * 查看说说
     *
     * @param talkId 说说id
     * @return {@link Result< TalkResp >}
     */
    @VisitLogger(value = "说说")
    @ApiOperation(value = "查看说说")
    @GetMapping("/talk/{talkId}")
    public Result<TalkResp> getTalkById(@PathVariable("talkId") Integer talkId) {
        return Result.success(talkService.getTalkById(talkId));
    }
}