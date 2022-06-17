package com.augenstern.controller;

import com.augenstern.domain.Code;
import com.augenstern.domain.FileUploadResultBean;
import com.augenstern.domain.ResultBean;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.FileUploadException;
import com.augenstern.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 */
@RestControllerAdvice
public class ProjectExceptionAdvice {
    public static final Logger LOGGER = LoggerFactory.getLogger("ProjectExceptionAdvice");

    @Resource
    private HttpServletRequest request;

    /**
     * 系统错误
     *
     */
    @ExceptionHandler(SystemException.class)
    public ResultBean doException(SystemException ex) {
        LOGGER.warn(request.getRemoteAddr() + " " + request.getRequestURL());
        LOGGER.warn("系统异常--" + ex.getMessage());
        return new ResultBean(null, ex.getCode(), ex.getMessage());
    }

    /**
     * 文件上传异常
     */
    @ExceptionHandler(FileUploadException.class)
    public FileUploadResultBean doException(FileUploadException ex) {
        LOGGER.warn(request.getRemoteAddr() + " " + request.getRequestURL());
        LOGGER.warn("文件上传异常--" + ex.getMessage());
        return new FileUploadResultBean(0, ex.getMessage());
    }

    /**
     * 用户错误
     *
     */
    @ExceptionHandler(BusinessException.class)
    public ResultBean doException(BusinessException ex) {
        LOGGER.warn(request.getRemoteAddr() + " " + request.getRequestURL());
        LOGGER.warn("用户异常--" + ex.getMessage());
        return new ResultBean(null, ex.getCode(), ex.getMessage());
    }

    /**
     * 其他错误
     *
     */
    @ExceptionHandler(Throwable.class)
    public ResultBean doException(Throwable ex) {
        LOGGER.error(request.getRemoteAddr() + " " + request.getRequestURL());
        LOGGER.error("未知错误--" + ex.getMessage());
        return new ResultBean(null, Code.SYSTEM_UNKNOWN_ERR, "未知错误");
    }
}
