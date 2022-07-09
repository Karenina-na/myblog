package com.augenstern.controller;

import com.augenstern.entity.Code;
import com.augenstern.entity.controller.ImgUploadResult;
import com.augenstern.entity.controller.ResultBean;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.FileUploadException;
import com.augenstern.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 */
@RestControllerAdvice
@Slf4j
public class ProjectExceptionAdvice {

    @Resource
    private HttpServletRequest request;

    /**
     * 系统错误
     *
     */
    @ExceptionHandler(SystemException.class)
    public ResultBean doException(SystemException ex) {
        log.warn(request.getRemoteAddr() + " " + request.getRequestURL());
        log.warn("系统异常--" + ex.getMessage());
        return new ResultBean(null, ex.getCode(), ex.getMessage());
    }

    /**
     * 图片上传异常
     */
    @ExceptionHandler(FileUploadException.class)
    public ImgUploadResult doException(FileUploadException ex) {
        log.warn(request.getRemoteAddr() + " " + request.getRequestURL());
        log.warn("文件上传异常--" + ex.getMessage());
        return new ImgUploadResult(0, ex.getMessage());
    }

    /**
     * 用户错误
     *
     */
    @ExceptionHandler(BusinessException.class)
    public ResultBean doException(BusinessException ex) {
        log.warn(request.getRemoteAddr() + " " + request.getRequestURL());
        log.warn("用户异常--" + ex.getMessage());
        return new ResultBean(null, ex.getCode(), ex.getMessage());
    }

    /**
     * 其他错误
     *
     */
    @ExceptionHandler(Throwable.class)
    public ResultBean doException(Throwable ex) {
        log.error(request.getRemoteAddr() + " " + request.getRequestURL());
        log.error("未知错误--" + ex.getMessage());
        return new ResultBean(null, Code.SYSTEM_UNKNOWN_ERR, "未知错误");
    }
}
