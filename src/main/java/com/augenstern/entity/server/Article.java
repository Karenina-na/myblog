package com.augenstern.entity.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 文章对象封装
 */
@ApiModel(description = "文章对象封装")
@Data
public class Article implements Serializable {

    @ApiModelProperty("标签列表")
    public List<String> tags;
    @ApiModelProperty("日期")
    private String date;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("内容")
    private String messages;
    @ApiModelProperty("id")
    private Integer id;
}
