package com.augenstern.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 前端富文本编辑器上传数据返回格式
 */
@ApiModel(description = "前端富文本编辑器上传数据返回格式")
public class FileUploadResultBean {

    @ApiModel(description = "内部数据类")
    public static class data{

        public data(String url) {
            this.url = url;
        }

        public data(String url, String alt) {
            this.url = url;
            this.alt = alt;
        }

        public data(String url, String alt, String href) {
            this.url = url;
            this.alt = alt;
            this.href = href;
        }

        @ApiModelProperty("图片src")
        private String url;

        @ApiModelProperty("图片描述文字")
        private String alt;

        @ApiModelProperty("图片链接")
        private String href;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        @Override
        public String toString() {
            return "data{" +
                    "url='" + url + '\'' +
                    ", alt='" + alt + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }

    @ApiModelProperty("状态码")
    private int errno;

    @ApiModelProperty("数据")
    private data data;

    @ApiModelProperty("失败信息")
    private String message;

    public FileUploadResultBean(int errno, data data) {
        this.errno = errno;
        this.data = data;
    }

    public FileUploadResultBean(int errno, String message) {
        this.errno = errno;
        this.message = message;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public FileUploadResultBean.data getData() {
        return data;
    }

    public void setData(FileUploadResultBean.data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FileUploadResultBean{" +
                "errno=" + errno +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
