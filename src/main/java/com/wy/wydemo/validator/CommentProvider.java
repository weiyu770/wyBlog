package com.wy.wydemo.validator;

import cn.hutool.core.annotation.Link;
import com.wy.wydemo.model.vo.request.CommentReq;
import com.wy.wydemo.validator.groups.ArticleTalk;
import com.wy.wydemo.validator.groups.ParentIdNotNull;
import com.wy.wydemo.validator.groups.ParentIdNull;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.wy.wydemo.model.enums.CommentTypeEnum .*;

/**
 * @description: 评论分组校验器
 * @class: CommentProvider
 * @author: yu_wei
 * @create: 2024/11/07 23:51
 */
public class CommentProvider implements DefaultGroupSequenceProvider<CommentReq> {
    @Override
    public List<Class<?>> getValidationGroups(CommentReq commentReq) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(CommentReq.class);
        if (commentReq != null) {
            if (commentReq.getCommentType().equals(ARTICLE.getType()) || commentReq.getCommentType().equals(TALK.getType())) {
                defaultGroupSequence.add(ArticleTalk.class);
            }
            if (commentReq.getCommentType().equals(LINK.getType())) {
                defaultGroupSequence.add(Link.class);
            }
            if (Objects.isNull(commentReq.getParentId())) {
                defaultGroupSequence.add(ParentIdNull.class);
            }
            if (Objects.nonNull(commentReq.getParentId())) {
                defaultGroupSequence.add(ParentIdNotNull.class);
            }
        }
        return defaultGroupSequence;
    }
}