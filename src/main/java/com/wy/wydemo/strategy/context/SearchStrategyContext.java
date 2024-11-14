package com.wy.wydemo.strategy.context;

import com.wy.wydemo.model.vo.response.ArticleSearchResp;
import com.wy.wydemo.strategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.wy.wydemo.model.enums.SearchModeEnum;

import static com.wy.wydemo.model.enums.SearchModeEnum.getStrategy;


/**
 * @description: 搜索策略上下文
 * @class: SearchStrategyContext
 * @author: yu_wei
 * @create: 2024/11/06 10:40
 */
@Service
public class SearchStrategyContext {
    
    /**
     * 搜索模式
     */
    @Value("${search.mode}")
    private String searchMode;
    
    @Autowired
    private Map<String, SearchStrategy> searchStrategyMap;
    
    /**
     * 执行搜索策略
     *
     * @param keyword 关键字
     * @return {@link List <ArticleSearchVO>} 搜索文章
     */
    public List<ArticleSearchResp> executeSearchStrategy(String keyword) {
        return searchStrategyMap.get(getStrategy(searchMode)).searchArticle(keyword);
    }
    
}