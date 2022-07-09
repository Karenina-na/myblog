package com.augenstern.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "标签模型")
@Data
@TableName("tag")
public class TagBean {
    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;
    @ApiModelProperty("标签")
    private String tag;
}
