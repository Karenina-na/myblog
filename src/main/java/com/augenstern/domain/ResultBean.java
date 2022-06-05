package com.augenstern.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据交互格式
 */
@ApiModel(description = "前后端数据交互格式")
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

    public Integer getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(Integer totalPage) {
        TotalPage = totalPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
