package com.augenstern;

import com.augenstern.dao.FileUploadDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class FileUploadDaoTests {

    @Resource
    FileUploadDao fileUploadDao;

    /**
     * 测试存储图片资源
     */
    @Test
    public void addImageTest(){
        String name="测试图片名";
        String date="2021-6-29";
        fileUploadDao.addImage(name,date);
    }

    /**
     * 获取图片总数
     */
    @Test
    public void selectTotalImageTest(){
        System.out.println(fileUploadDao.selectTotalImage());
    }

    /**
     * 获取图片资源
     */
    @Test
    public void selectAllImageTest(){
        System.out.println(fileUploadDao.selectAllImage());
    }

    /**
     * 测试删除图片资源
     */
    @Test
    public void deleteImageTest(){
        fileUploadDao.deleteImage("测试图片名");
        System.out.println(fileUploadDao.selectAllImage());
    }
}
