package com.augenstern.exception;

/**
 * 文件上传异常
 */
public class FileUploadException extends Exception{

    private Integer code;

    public FileUploadException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public FileUploadException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
