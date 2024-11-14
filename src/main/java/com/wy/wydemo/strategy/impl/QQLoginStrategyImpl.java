package com.wy.wydemo.strategy.impl;

import com.wy.wydemo.config.properity.QQProperties;
import com.wy.wydemo.constant.SocialLoginConstant;
import com.wy.wydemo.exception.ServiceException;
import com.wy.wydemo.model.dto.*;
import com.wy.wydemo.model.enums.LoginTypeEnum;
import com.wy.wydemo.model.vo.request.CodeReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

/**
 * @description: QQ登录策略
 * @class: QQLoginStrategyImpl
 * @author: yu_wei
 * @create: 2024/11/04 15:32
 */
@Slf4j
@Service("qqLoginStrategyImpl")
public class QQLoginStrategyImpl extends AbstractLoginStrategyImpl {
    
    @Autowired
    private QQProperties qqProperties;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public SocialTokenDTO getSocialToken(CodeReq CodeRequest ) {
        // 获取Qq的Token
        TokenDTO qqToken = getQqToken(CodeRequest .getCode());
        // 获取用户OpenId
        QQTokenDTO userOpenId = getUserOpenId(qqToken.getAccess_token());
        // 返回token信息
        return SocialTokenDTO.builder()
                .openId(userOpenId.getOpenid())
                .accessToken(qqToken.getAccess_token())
                .loginType(LoginTypeEnum.QQ.getLoginType())
                .build();
    }
    
    @Override
    public SocialUserInfoDTO getSocialUserInfo(SocialTokenDTO socialToken) {
        // 定义请求参数
        MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>(3);
        queryMap.set(SocialLoginConstant.QQ_OPEN_ID, socialToken.getOpenId());
        queryMap.set(SocialLoginConstant.ACCESS_TOKEN, socialToken.getAccessToken());
        queryMap.set(SocialLoginConstant.OAUTH_CONSUMER_KEY, qqProperties.getAppId());
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(qqProperties.getUserInfoUrl());
        URI uri = uriComponentsBuilder.queryParams(queryMap).build().toUri();
        // 获取QQ返回的用户信息
        QqUserInfoDTO qqUserInfo = restTemplate.getForObject(uri, QqUserInfoDTO.class);
        // 返回用户信息
        return SocialUserInfoDTO.builder()
                .id(socialToken.getOpenId())
                .nickname(Objects.requireNonNull(qqUserInfo).getNickname())
                .avatar(qqUserInfo.getFigureurl_qq_1())
                .build();
    }
    
    /**
     * 获取QQ的Token
     *
     * @param code 第三方code
     * @return {@link TokenDTO} QQ的Token
     */
    private TokenDTO getQqToken(String code) {
        try {
            // 根据code换取accessToken
            MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>(6);
            // Gitee的Token请求参数
            queryMap.set(SocialLoginConstant.GRANT_TYPE, qqProperties.getGrantType());
            queryMap.set(SocialLoginConstant.CLIENT_ID, qqProperties.getAppId());
            queryMap.set(SocialLoginConstant.CLIENT_SECRET, qqProperties.getAppKey());
            queryMap.set(SocialLoginConstant.CODE, code);
            queryMap.set(SocialLoginConstant.REDIRECT_URI, qqProperties.getRedirectUrl());
            queryMap.set(SocialLoginConstant.FMT, "json");
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(qqProperties.getAccessTokenUrl());
            URI uri = uriComponentsBuilder.queryParams(queryMap).build().toUri();
            return restTemplate.getForObject(uri, TokenDTO.class);
        } catch (Exception e) {
            log.error("getQqToken is error, {}", e.getMessage());
            throw new ServiceException("QQ登录错误");
        }
    }
    
    /**
     * 获取用户的OpenId
     *
     * @return 用户的OpenId
     */
    private QQTokenDTO getUserOpenId(String accessToken) {
        try {
            // 返回用户OpenId
            MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>(2);
            // 请求参数
            queryMap.set(SocialLoginConstant.ACCESS_TOKEN, accessToken);
            queryMap.set(SocialLoginConstant.FMT, "json");
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(qqProperties.getUserOpenIdUrl());
            URI uri = uriComponentsBuilder.queryParams(queryMap).build().toUri();
            return restTemplate.getForObject(uri, QQTokenDTO.class);
        } catch (RestClientException e) {
            log.info("getUserOpenId is error, {}", e.getMessage());
            throw new ServiceException("QQ登录错误");
        }
    }
}