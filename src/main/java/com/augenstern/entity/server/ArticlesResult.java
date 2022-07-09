package com.augenstern.entity.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 批量文章对象封装
 */
@ApiModel(description = "批量文章对象封装")
@AllArgsConstructor
@Data
public class ArticlesResult {
    @ApiModelProperty("文章列表")
    List<Article> articles;
    @ApiModelProperty("总数")
    Integer total;
    
}
