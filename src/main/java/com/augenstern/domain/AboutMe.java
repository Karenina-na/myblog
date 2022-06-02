package com.augenstern.domain;

import java.io.Serializable;

/**
 * 必要数据bean
 */
public class AboutMe implements Serializable {

    private String author;

    private String introduce;

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
