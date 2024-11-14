package com.wy.wydemo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:文章标签
 * @class: ArticleTag
 * @author: yu_wei
 * @create: 2024/11/02 14:00
 */
@TableName("t_article_tag")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTag {
    
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 文章id
     */
    private Integer articleId;
    
    /**
     * 标签id
     */
    private Integer tagId;
    
}