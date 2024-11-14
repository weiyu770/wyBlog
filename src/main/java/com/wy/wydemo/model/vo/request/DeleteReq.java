package com.wy.wydemo.model.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: 逻辑删除
 * @class: DeleteReq
 * @author: yu_wei
 * @create: 2024/11/05 19:44
 */
@Data
@ApiModel(description = "逻辑删除")
public class DeleteReq {
    
    /**
     * id列表
     */
    @NotEmpty(message = "id不能为空")
    @ApiModelProperty(value = "id列表", required = true)
    private List<Integer> idList;
    
    /**
     * 是否删除 (0否 1是)
     */
    @NotNull(message = "状态值不能为空")
    @ApiModelProperty(value = "是否删除 (0否 1是)", required = true)
    private Integer isDelete;
}
