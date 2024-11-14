package com.wy.wydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.wydemo.model.entity.Talk;
import com.wy.wydemo.model.vo.query.PageQuery;
import com.wy.wydemo.model.vo.query.TalkQuery;
import com.wy.wydemo.model.vo.response.TalkBackInfoResp;
import com.wy.wydemo.model.vo.response.TalkBackResp;
import com.wy.wydemo.model.vo.response.TalkResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @class: TalkMapper
 * @author: yu_wei
 * @create: 2024/11/08 00:01
 */
@Mapper
public interface TalkMapper extends BaseMapper<Talk> {
    
    /**
     * 查询后台说说列表
     *
     * @param talkQuery 说说查询条件
     * @return 后台说说列表
     */
    List<TalkBackResp> selectBackTalkList(@Param("param") TalkQuery talkQuery);
    /**
     * 根据id查询后台说说
     *
     * @param talkId 说说id
     * @return 后台说说
     */
    TalkBackInfoResp selectTalkBackById(@Param("talkId") Integer talkId);
    
    /**
     * 查询说说列表
     *
     * @param pageQuery 分页查询条件
     * @return 说说列表
     */
    List<TalkResp> selectTalkList(@Param("param") PageQuery pageQuery);
    
    /**
     * 根据id查询说说
     *
     * @param talkId 说说id
     * @return 说说
     */
    TalkResp selectTalkById(@Param("talkId") Integer talkId);
}
