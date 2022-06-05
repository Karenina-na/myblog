package com.augenstern.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 前端页码类型交互格式
 */
@ApiModel(description = "前后端页码类型交互格式")
public class TypeAndPageBean {
    @ApiModelProperty("类型")
    String type;
    @ApiModelProperty("页码")
    int page;

    @Override
    public String toString() {
        return "TypeAndPageBean{" +
                "type='" + type + '\'' +
                ", page=" + page +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
