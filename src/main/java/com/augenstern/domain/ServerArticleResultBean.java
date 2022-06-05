package com.augenstern.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 业务层返回数据格式
 */
@ApiModel(description = "业务层数据数据格式")
public class ServerArticleResultBean {
    @ApiModelProperty("文章列表")
    List<ArticleBean> articleBean;
    @ApiModelProperty("文章总数")
    Integer total;

    public ServerArticleResultBean(List<ArticleBean> articleBean, Integer total) {
        this.articleBean = articleBean;
        this.total = total;
    }

    @Override
    public String toString() {
        return "ServerArticleResultBean{" +
                "articleBean=" + articleBean +
                ", total=" + total +
                '}';
    }

    public List<ArticleBean> getArticleBean() {
        return articleBean;
    }

    public void setArticleBean(List<ArticleBean> articleBean) {
        this.articleBean = articleBean;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
