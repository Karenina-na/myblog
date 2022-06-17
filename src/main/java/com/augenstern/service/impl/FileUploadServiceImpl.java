package com.augenstern.service.impl;

import com.augenstern.domain.Code;
import com.augenstern.exception.FileUploadException;
import com.augenstern.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public String UploadImg(MultipartFile file , HttpServletRequest request) throws  FileUploadException {
        String filename = file.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img";
        if (!new File(filePath).exists()){
            new File(filePath).mkdirs();
        }
        File dest = new File(filePath + File.separator +"_"+filename);
        try {
            file.transferTo(dest);
        }catch (Exception e){
            throw new FileUploadException("文件写入本地错误", Code.File_SAVE_ERR);
        }
        System.out.println(dest.getName());
        return dest.getName();
    }
}
