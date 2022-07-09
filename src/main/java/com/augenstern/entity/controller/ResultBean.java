package com.augenstern.entity.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据交互格式
 */
@ApiModel(description = "前后端数据交互格式")
@Data
public class ResultBean {
    @ApiModelProperty("数据")
    private Object data;
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("后端错误信息")
    private String msg;
    @ApiModelProperty("总页数")
    private Integer TotalPage;

    public ResultBean(Object data, Integer code, Integer totalPage) {
        this.data = data;
        this.code = code;
        TotalPage = totalPage;
    }

    public ResultBean(Object data, Integer code) {
        this.data = data;
        this.code = code;
    }

    public ResultBean(Object data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }


}
