package com.augenstern.service.impl;

import com.augenstern.dao.FileUploadDao;
import com.augenstern.domain.Code;
import com.augenstern.domain.ServerSourceResultBean;
import com.augenstern.domain.SourceBean;
import com.augenstern.exception.FileUploadException;
import com.augenstern.exception.SystemException;
import com.augenstern.service.FileUploadService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private FileUploadDao fileUploadDao;

    @Override
    public String UploadImg(MultipartFile file , HttpServletRequest request) throws  FileUploadException {

        long timeInMillis = Calendar.getInstance().getTimeInMillis();

        String filename = timeInMillis+"_"+file.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+"/img";
        if (!new File(filePath).exists()){
            new File(filePath).mkdirs();
        }
        File dest = new File(filePath + File.separator+filename);

        fileUploadDao.addImage(dest.getPath(), String.valueOf(new Date()));
        try {
            file.transferTo(dest);
        }catch (Exception e){
            throw new FileUploadException(e.getMessage(), Code.File_SAVE_ERR);
        }
        return dest.getPath();
    }

    @Override
    public ServerSourceResultBean SelectImg(int page) {
        PageHelper.startPage(page, 15);
        List<SourceBean> sourceBeans = fileUploadDao.selectAllImage();
        int total = fileUploadDao.selectTotalImage();
        return new ServerSourceResultBean(sourceBeans,total);
    }

    @Override
    public boolean DeleteImg(String name) throws SystemException {
        Path path = Paths.get(name);
        try {
            Files.delete(path);   //返回值void
            int count = fileUploadDao.deleteImage(name);
            return count == 1;
        }catch (Exception e){
            throw new SystemException("删除文件错误--"+e.getMessage(),Code.DELETE_ERR);
        }
    }
}
