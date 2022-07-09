package com.augenstern.entity.server;

import com.augenstern.entity.dao.SourceBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 批量静态资源对象封装
 */
@ApiModel(description = "批量静态资源对象封装")
@Data
@AllArgsConstructor
public class SourcesResult {

    @ApiModelProperty("资源数据列表")
    private List<SourceBean> sources;

    @ApiModelProperty("总数")
    private Integer total;

}
