package com.augenstern.service.impl;

import com.augenstern.domain.Code;
import com.augenstern.exception.FileUploadException;
import com.augenstern.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Calendar;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public String UploadImg(MultipartFile file , HttpServletRequest request) throws  FileUploadException {

        long timeInMillis = Calendar.getInstance().getTimeInMillis();

        String filename = timeInMillis+"_"+file.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+"/img";
        if (!new File(filePath).exists()){
            new File(filePath).mkdirs();
        }
        File dest = new File(filePath + File.separator+filename);
        try {
            file.transferTo(dest);
        }catch (Exception e){
            throw new FileUploadException(e.getMessage(), Code.File_SAVE_ERR);
        }
        System.out.println(dest.getName());
        return dest.getPath();
    }
}
