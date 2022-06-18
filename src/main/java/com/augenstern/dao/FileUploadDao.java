package com.augenstern.dao;

import com.augenstern.domain.SourceBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 上传文件数据层
 */
@CacheNamespaceRef(name = "com.augenstern.dao.Cache")
public interface FileUploadDao {

    /**
     * 插入图片资源
     */
    @Insert("insert into Source(type,name,date) values ('img',#{name},#{date}); ")
    void addImage(String name,String date);
    /**
     * 获取图片总数
     */
    @Select("select count(Source.id) from Source where type='img'")
    int selectTotalImage();
    /**
     * 获取图片资源
     */
    @Select("select * from Source where type='img' order by Source.id desc")
    @ResultMap("Source")
    List<SourceBean> selectAllImage();
    /**
     * 删除图片资源
     *
     * @return
     */
    @Delete("delete from Source where type='img' and name=#{name}")
    int deleteImage(String name);
}
