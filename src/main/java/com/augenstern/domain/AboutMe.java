package com.augenstern.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 必要数据bean
 */
@ApiModel(description = "AboutMe数据封装")
public class AboutMe implements Serializable {

    @ApiModelProperty("作者")
    private String author;

    @Override
    public String toString() {
        return "AboutMe{" +
                "author='" + author + '\'' +
                ", introduce='" + introduce + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }


    @ApiModelProperty("简介")
    private String introduce;

    @ApiModelProperty("公告")
    private String notice;

    public AboutMe(String author, String introduce, String notice) {
        this.author = author;
        this.introduce = introduce;
        this.notice = notice;
    }

    public AboutMe(){}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
