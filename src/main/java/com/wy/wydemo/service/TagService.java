package com.wy.wydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.wydemo.model.entity.Tag;
import com.wy.wydemo.model.vo.query.ArticleConditionQuery;
import com.wy.wydemo.model.vo.query.TagQuery;
import com.wy.wydemo.model.vo.request.TagReq;
import com.wy.wydemo.model.vo.response.*;

import javax.swing.text.html.parser.TagElement;
import java.util.List;

/**
 * @description:
 * @class: TagService
 * @author: yu_wei
 * @create: 2024/11/05 10:54
 */
public interface TagService extends IService<Tag> {
    
    /**
     * 查看后台标签列表
     * @param tagQuery
     * @return
     */
    PageResult<TagBackResp> listTagBackVO(TagQuery tagQuery);
    
    
    /**
     * 添加标签
     * @param tag
     */
    void addTag(TagReq tag);
    
    
    /**
     * 删除标签
     * @param tagIdList
     */
    void deleteTag(List<Integer> tagIdList);
    
    
    /**
     * 更新标签
     * @param tag
     */
    void updateTag(TagReq tag);
    
    
    /**
     * 查看标签选项
     * @return
     */
    List<TagOptionResp> listTagOption();
    
    
    /**
     * 查看标签列表
     * @return
     */
    List<TagResp> listTagVO();
    
    
    /**
     * 查看标签下的文章
     * @param articleConditionQuery
     * @return
     */
    ArticleConditionList listArticleTag(ArticleConditionQuery articleConditionQuery);
    
}
