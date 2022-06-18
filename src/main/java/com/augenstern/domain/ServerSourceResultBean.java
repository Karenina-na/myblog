package com.augenstern.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 业务层数据资源幸喜封装
 */
@ApiModel(description = "业务层资源信息传递Bean")
public class ServerSourceResultBean {

    @ApiModelProperty("资源数据")
    private List<SourceBean> source;

    @ApiModelProperty("总数")
    private int total;

    public ServerSourceResultBean(List<SourceBean> sourceBeans, int total) {
        this.source = sourceBeans;
        this.total = total;
    }

    @Override
    public String toString() {
        return "ServerSourceResultBean{" +
                "source=" + source +
                ", total=" + total +
                '}';
    }

    public List<SourceBean> getSource() {
        return source;
    }

    public void setSource(List<SourceBean> source) {
        this.source = source;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
