package com.augenstern.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 文章对象Bean
 */
@ApiModel(description = "文章对象封装")
public class ArticleBean implements Serializable {

    @ApiModelProperty("标签列表")
    public List<String> tags;
    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("日期")
    private String date;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("内容")
    private String messages;

    @Override
    public String toString() {
        return "ArticleBean{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", messages='" + messages + '\'' +
                ", tags=" + tags +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tag) {
        this.tags = tag;
    }
}
