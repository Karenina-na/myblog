package com.augenstern.entity.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "AboutMe模型")
@Data
@TableName("aboutme")
public class AboutMeBean implements Serializable {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("介绍")
    private String introduce;
    @ApiModelProperty("公告")
    private String notice;
}
