package com.wy.wydemo.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.mapper.ArticleMapper;
import com.wy.wydemo.mapper.ArticleTagMapper;
import com.wy.wydemo.mapper.TagMapper;
import com.wy.wydemo.model.entity.ArticleTag;
import com.wy.wydemo.model.entity.Tag;
import com.wy.wydemo.model.vo.query.ArticleConditionQuery;
import com.wy.wydemo.model.vo.query.TagQuery;
import com.wy.wydemo.model.vo.request.TagReq;
import com.wy.wydemo.model.vo.response.*;
import com.wy.wydemo.service.TagService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @class: TagServiceImpl
 * @author: yu_wei
 * @create: 2024/11/05 10:54
 */
@Service
@Slf4j
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    
    @Autowired
    private TagMapper tagMapper;
    
    @Autowired
    private ArticleTagMapper articleTagMapper;
    
    @Autowired
    private ArticleMapper articleMapper;
    
    
    /**
     * 查看后台标签列表
     * @param tagQuery
     * @return
     */
    @Override
    public PageResult<TagBackResp> listTagBackVO(TagQuery tagQuery) {
        // 查询标签数量
        Long count = tagMapper.selectCount(new LambdaQueryWrapper<Tag>()
                .like(StringUtils.hasText(tagQuery.getKeyword()), Tag::getTagName,
                        tagQuery.getKeyword()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询标签列表
        List<TagBackResp> tagList = tagMapper.selectBackTagList(tagQuery);
        return new PageResult<>(tagList, count);
    }
    
    /**
     * 添加标签
     * @param tag
     */
    @Override
    public void addTag(TagReq tag) {
        // 标签是否存在
        Tag existTag = tagMapper.selectOne(new LambdaQueryWrapper<Tag>()
                .select(Tag::getId)
                .eq(Tag::getTagName, tag.getTagName()));
        Assert.isNull(existTag, tag.getTagName() + "标签已存在");
        // 添加新标签
        Tag newTag = Tag.builder()
                .tagName(tag.getTagName())
                .build();
        baseMapper.insert(newTag);
    }
    
    
    /**
     * 删除标签
     * @param tagIdList
     */
    @Override
    public void deleteTag(List<Integer> tagIdList) {
        // 标签下是否有文章
        Long count = articleTagMapper.selectCount(new LambdaQueryWrapper<ArticleTag>()
                .in(ArticleTag::getTagId, tagIdList));
        Assert.isFalse(count > 0, "删除失败，标签下存在文章");
        // 批量删除标签
        tagMapper.deleteBatchIds(tagIdList);
    }
    
    
    /**
     * 更新标签
     * @param tag
     */
    @Override
    public void updateTag(TagReq tag) {
        // 标签是否存在
        Tag existTag = tagMapper.selectOne(new LambdaQueryWrapper<Tag>()
                .select(Tag::getId)
                .eq(Tag::getTagName, tag.getTagName()));
        Assert.isFalse(Objects.nonNull(existTag) && !existTag.getId().equals(tag.getId()),
                tag.getTagName() + "标签已存在");
        // 修改标签
        Tag newTag = Tag.builder()
                .id(tag.getId())
                .tagName(tag.getTagName())
                .build();
        baseMapper.updateById(newTag);
    }
    
    /**
     * 查看标签选项
     * @return
     */
    @Override
    public List<TagOptionResp> listTagOption() {
        return tagMapper.selectTagOptionList();
    }
    
    /**
     * 查看标签列表
     * @return
     */
    @Override
    public List<TagResp> listTagVO() {
        return tagMapper.selectTagVOList();
    }
    
    
    /**
     * 查看标签下的文章
     * @param articleConditionQuery
     * @return
     */
    @Override
    public ArticleConditionList listArticleTag(ArticleConditionQuery articleConditionQuery) {
        List<ArticleConditionResp> articleConditionList = articleMapper.selectArticleListByCondition(articleConditionQuery);
        String name = tagMapper.selectOne(new LambdaQueryWrapper<Tag>()
                        .select(Tag::getTagName)
                        .eq(Tag::getId, articleConditionQuery.getTagId()))
                .getTagName();
        return ArticleConditionList.builder()
                .articleConditionVOList(articleConditionList)
                .name(name)
                .build();
    }
    
    
}
