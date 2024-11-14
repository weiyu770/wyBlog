package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 文章实体类
 * @class: Article
 * @author: yu_wei
 * @create: 2024/11/02 13:53
 */
@TableName("t_article")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    
    /**
     * 文章id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 文章作者
     */
    private Long userId;
    
    /**
     * 文章分类
     */
    private Integer categoryId;
    
    /**
     * 文章缩略图
     */
    private String articleCover;
    
    /**
     * 文章标题
     */
    private String articleTitle;
    
    /**
     * 文章摘要
     */
    private String articleDesc;
    
    /**
     * 文章内容
     */
    private String articleContent;
    
    /**
     * 文章类型 (1原创 2转载 3翻译)
     */
    private Integer articleType;
    
    /**
     * 是否置顶 (0否 1是)
     */
    private Integer isTop;
    
    /**
     * 是否删除 (0否 1是)
     */
    private Integer isDelete;
    
    /**
     * 是否推荐 (0否 1是)
     */
    private Integer isRecommend;
    
    /**
     * 状态 (1公开 2私密 3草稿)
     */
    private Integer status;
    
    /**
     * 发表时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
}