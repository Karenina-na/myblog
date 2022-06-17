package com.augenstern.controller;

import com.augenstern.domain.Code;
import com.augenstern.domain.FileUploadResultBean;
import com.augenstern.domain.ResultBean;
import com.augenstern.exception.FileUploadException;
import com.augenstern.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 文件上传控制器
 */
@RestController
@Api(tags ="文件上传控制器")
@RequestMapping("/upLoad")
public class FileController {

    @Resource
    private FileUploadService fileUploadService;

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @ApiOperation("上传图片")
    @PostMapping("/img")
    public FileUploadResultBean UpLoadImage(@RequestParam("file") @ApiParam("MultipartFile 文件类") MultipartFile file,
                                            @ApiParam("HttpServletRequest 请求类") HttpServletRequest request) throws  FileUploadException {
        if (file.isEmpty()){
            return new FileUploadResultBean(1, "图片为空");
        }
        String result = fileUploadService.UploadImg(file,request);
        if (result==null){
            return new FileUploadResultBean(1,"图片上传错误");

        }
        else {
            return new FileUploadResultBean(0,new FileUploadResultBean.data(result));
        }
    }
}
