package com.augenstern.entity.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 前端富文本编辑器上传图片返回格式
 */
@ApiModel(description = "前端富文本编辑器上传图片返回格式")
@Data
public class ImgUploadResult {

    @ApiModelProperty("状态码")
    private Integer errno;
    @ApiModelProperty("数据")
    private data data;
    @ApiModelProperty("失败信息")
    private String message;

    public ImgUploadResult(int errno, data data) {
        this.errno = errno;
        this.data = data;
    }

    public ImgUploadResult(int errno, String message) {
        this.errno = errno;
        this.message = message;
    }

    @ApiModel(description = "内部数据类")
    @Data
    @AllArgsConstructor
    public static class data {

        @ApiModelProperty("图片src")
        private String url;
        @ApiModelProperty("图片描述文字")
        private String alt;
        @ApiModelProperty("图片链接")
        private String href;

        public data(String url) {
            this.url = url;
        }

        public data(String url, String alt) {
            this.url = url;
            this.alt = alt;
        }


    }


}
