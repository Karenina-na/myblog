package com.augenstern.service.impl;

import com.augenstern.dao.FileUploadDao;
import com.augenstern.entity.Code;
import com.augenstern.entity.dao.SourceBean;
import com.augenstern.entity.server.Article;
import com.augenstern.entity.server.SourcesResult;
import com.augenstern.exception.FileUploadException;
import com.augenstern.exception.SystemException;
import com.augenstern.service.FileUploadService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private FileUploadDao fileUploadDao;

    @Value("${URL}")
    private String URL;

    @Override
    public String UploadImg(MultipartFile file , HttpServletRequest request) throws  FileUploadException {

        long timeInMillis = Calendar.getInstance().getTimeInMillis();

        String filename = timeInMillis+"_"+file.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+File.separator+"image";
        if (!new File(filePath).exists()){
            new File(filePath).mkdirs();
        }
        File dest = new File(filePath + File.separator+filename);

        fileUploadDao.addImage(filename, String.valueOf(new Date()));
        try {
            file.transferTo(dest);
        }catch (Exception e){
            throw new FileUploadException(e.getMessage(), Code.File_SAVE_ERR);
        }
        return URL+"/"+"image"+File.separator+filename;
    }

    @Override
    public SourcesResult SelectImg(int page) {
        Page<SourceBean> Page=new Page<>(page,6);
        List<SourceBean> sourceBeans = fileUploadDao.selectAllImage(Page).getRecords();
        for (SourceBean sourceBean : sourceBeans) {
            String name = sourceBean.getName();
            name=URL+"/" + "image"+File.separator+name;
            sourceBean.setName(name);
        }
        int total = fileUploadDao.selectTotalImage();
        return new SourcesResult(sourceBeans,total);
    }

    @Override
    public boolean DeleteImg(String name) throws SystemException {
        String filePath = System.getProperty("user.dir")+File.separator+"image"+ File.separator;
        Path path = Paths.get(filePath+name);
        try {
            Files.delete(path);   //返回值void
            int count = fileUploadDao.deleteImage(name);
            return count == 1;
        }catch (Exception e){
            throw new SystemException("删除文件错误--"+e.getMessage(),Code.DELETE_ERR);
        }
    }
}
