package com.wy.wydemo.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 文章贡献统计Response
 * @class: ArticleStatisticsResp
 * @author: yu_wei
 * @create: 2024/11/07 17:25
 */
@Data
@ApiModel(description = "文章贡献统计Response")
public class ArticleStatisticsResp {
    
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private String date;
    
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer count;
}
