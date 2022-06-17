package com.augenstern.service;

import com.augenstern.exception.FileUploadException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件上传业务层
 */
public interface FileUploadService {

    @Transactional(timeout = 5, rollbackFor = Exception.class)
    String UploadImg(MultipartFile file, HttpServletRequest request) throws  FileUploadException;
}
