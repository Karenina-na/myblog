package com.augenstern.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "静态资源模型")
@Data
@TableName("source")
public class SourceBean {
    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO, value = "id")
    private Integer id;

    @ApiModelProperty("资源类型")
    private String type;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("日期")
    private String date;
}
