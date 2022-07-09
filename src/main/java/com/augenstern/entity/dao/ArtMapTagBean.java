package com.augenstern.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Article映射Tag模型")
@Data
@TableName("artmaptag")
public class ArtMapTagBean {
    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    @ApiModelProperty("ArticleBean Id")
    private int a_id;
    @ApiModelProperty("TagBean Id")
    private int t_id;
}
