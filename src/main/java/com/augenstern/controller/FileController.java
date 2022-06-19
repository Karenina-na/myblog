package com.augenstern.controller;

import com.augenstern.domain.Code;
import com.augenstern.domain.FileUploadResultBean;
import com.augenstern.domain.ResultBean;
import com.augenstern.domain.ServerSourceResultBean;
import com.augenstern.exception.FileUploadException;
import com.augenstern.exception.SystemException;
import com.augenstern.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
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

    /**
     * 分页获取图片
     * @param page
     * @return
     */
    @ApiOperation("获取图片")
    @GetMapping("/img/{page}")
    public ResultBean GetImage(@ApiParam("页码") @PathVariable Integer page)  {
        ServerSourceResultBean data = fileUploadService.SelectImg(page);
        if (data==null) {
            return new ResultBean(null, Code.GET_ERR);
        }
        else{
            return new ResultBean(data.getSource(),Code.GET_OK,data.getTotal());
        }
    }


    /**
     * 删除图片
     * @param
     * @return
     */
    @ApiOperation("删除图片")
    @DeleteMapping("/img/{name}")
    public ResultBean DeleteImage(@ApiParam("文件名") @PathVariable String name) throws SystemException {
        System.out.println(name);
        boolean result = fileUploadService.DeleteImg(name);
        if (result) {
            return new ResultBean(true, Code.DELETE_OK);
        } else {
            return new ResultBean(false, Code.DELETE_ERR, "删除错误");
        }
    }
}
