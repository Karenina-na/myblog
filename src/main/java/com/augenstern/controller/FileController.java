package com.augenstern.controller;

import com.augenstern.entity.Code;
import com.augenstern.entity.controller.ImgUploadResult;
import com.augenstern.entity.controller.ResultBean;
import com.augenstern.entity.server.SourcesResult;
import com.augenstern.exception.FileUploadException;
import com.augenstern.exception.SystemException;
import com.augenstern.service.FileService;
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
    private FileService fileService;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @ApiOperation("上传图片")
    @PostMapping("/img")
    public ImgUploadResult UpLoadImage(@RequestParam("file") @ApiParam("MultipartFile 文件类") MultipartFile file,
                                       @ApiParam("HttpServletRequest 请求类") HttpServletRequest request) throws  FileUploadException {
        if (file.isEmpty()){
            return new ImgUploadResult(1, "图片为空");
        }
        String result = fileService.UploadImg(file,request);
        if (result==null){
            return new ImgUploadResult(1,"图片上传错误");

        }
        else {
            return new ImgUploadResult(0,new ImgUploadResult.data(result));
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
        SourcesResult data = fileService.SelectImg(page);
        if (data==null) {
            return new ResultBean(null, Code.GET_ERR);
        }
        else{
            return new ResultBean(data.getSources(),Code.GET_OK,data.getTotal());
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
        boolean result = fileService.DeleteImg(name);
        if (result) {
            return new ResultBean(true, Code.DELETE_OK);
        } else {
            return new ResultBean(false, Code.DELETE_ERR, "删除错误");
        }
    }
}
