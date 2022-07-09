package com.augenstern.entity.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 前端页码类型交互格式
 */
@ApiModel(description = "前后端页码类型交互格式")
@Data
public class TypeAndPageBean {
    @ApiModelProperty("类型")
    String type;
    @ApiModelProperty("页码")
    Integer page;
}
