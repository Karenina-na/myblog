package com.augenstern.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 资源信息Bean
 */
@ApiModel(description = "资源信息Bean")
public class SourceBean implements Serializable {

    @ApiModelProperty("资源类型")
    private String type;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("日期")
    private String date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SourceBean{" +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
