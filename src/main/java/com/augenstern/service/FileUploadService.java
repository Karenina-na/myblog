package com.augenstern.service;

import com.augenstern.domain.ServerSourceResultBean;
import com.augenstern.domain.SourceBean;
import com.augenstern.exception.FileUploadException;
import com.augenstern.exception.SystemException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件上传业务层
 */
public interface FileUploadService {

    /**
     * 上传图片
     */
    @Transactional(timeout = 5, rollbackFor = Exception.class)
    String UploadImg(MultipartFile file, HttpServletRequest request) throws  FileUploadException;

    /**
     * 分页获取图片资源
     */
    @Transactional(timeout = 5,rollbackFor = Exception.class,readOnly = true)
    ServerSourceResultBean SelectImg(int page);

    /**
     * 删除文件
     */
    @Transactional(timeout = 5, rollbackFor = Exception.class)
    boolean DeleteImg(String name) throws SystemException;
}
