package com.augenstern.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Article模型")
@Data
@TableName("article")
public class ArticleBean {
    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    @ApiModelProperty("日期")
    private String date;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("内容")
    private String messages;
}
