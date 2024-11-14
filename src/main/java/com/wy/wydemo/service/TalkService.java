package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Talk;
import com.wy.wydemo.model.vo.query.PageQuery;
import com.wy.wydemo.model.vo.query.TalkQuery;
import com.wy.wydemo.model.vo.request.TalkReq;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.model.vo.response.TalkBackInfoResp;
import com.wy.wydemo.model.vo.response.TalkBackResp;
import com.wy.wydemo.model.vo.response.TalkResp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description:
 * @class: TalkService
 * @author: yu_wei
 * @create: 2024/11/08 17:20
 */
public interface TalkService  extends IService<Talk> {
    
    /**
     * 查看后台说说列表
     * @param talkQuery
     * @return
     */
    PageResult<TalkBackResp> listTalkBackVO(TalkQuery talkQuery);
    
    
    /**
     * 添加说说
     * @param talk
     */
    void addTalk(TalkReq talk);
    
    /**
     * 上传说说封面
     * @param file
     * @return
     */
    String uploadTalkCover(MultipartFile file);
    
    /**
     * 删除说说
     * @param talkId
     */
    void deleteTalk(Integer talkId);
    
    /**
     * 修改说说
     * @param talk
     */
    void updateTalk(TalkReq talk);
    
    /**
     * 编辑说说
     * @param talkId
     * @return
     */
    TalkBackInfoResp editTalk(Integer talkId);
    
    
    /**
     * 查看首页说说
     * @return
     */
    List<String> listTalkHome();
    
    /**
     * 查看说说列表
     * @param pageQuery
     * @return
     */
    PageResult<TalkResp> listTalkVO(PageQuery pageQuery);
    
    /**
     * 查看说说
     * @param talkId
     * @return
     */
    TalkResp getTalkById(Integer talkId);
}
