package com.augenstern.dao;

import com.augenstern.entity.dao.SourceBean;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

/**
 * 上传文件数据层
 */
@Mapper
public interface FileUploadDao {

    /**
     * 插入图片资源
     */
    @Insert("insert into source(type,name,date) values ('img',#{name},#{date}); ")
    void addImage(String name,String date);
    /**
     * 获取图片总数
     */
    @Select("select count(source.id) from source where type='img'")
    int selectTotalImage();
    /**
     * 获取图片资源
     */
    @Select("select * from source where type='img' order by source.id desc")
    @ResultMap("Source")
    Page<SourceBean> selectAllImage(@Param("page")Page<SourceBean> page);
    /**
     * 删除图片资源
     *
     * @return
     */
    @Delete("delete from source where type='img' and name=#{name}")
    int deleteImage(String name);
}
