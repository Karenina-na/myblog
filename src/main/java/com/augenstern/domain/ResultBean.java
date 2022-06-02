package com.augenstern.domain;

/**
 * 数据交互格式
 */
public class ResultBean {
    private Object data;
    private Integer code;
    private String msg;
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
