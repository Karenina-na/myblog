package com.augenstern.dao;

import com.augenstern.entity.dao.AboutMeBean;
import com.augenstern.entity.dao.UserBean;
import com.augenstern.entity.server.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 操作数据库层
 */
@CacheNamespaceRef(name = "com.augenstern.dao.Cache")
@Mapper
public interface MyDao {

    /**
     * 增加标签
     */
    void addTags(@Param("id")int id, @Param("tags")List<String> tags);

    /**
     * id删除标签
     */
    @Delete("delete from artmaptag where a_id=#{id}")
    void deleteTagsByArticleId(int id);

    /**
     * 增
     */
    @Insert("insert into article(date, title, author, messages) " +
            "values (#{date},#{title},#{author},#{messages})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void addArticle(Article article);

    /**
     * 删
     */
    @Delete("delete from article where id=#{id}")
    void deleteArticle(int id);

    /**
     * 改
     */
    void updateArticle(Article articleBean);

    /**
     * 查询管理账号密码
     */
    @Select("select * from user")
    @Options(useCache = false)
    List<UserBean> selectRoot();

    /**
     * 更改个人信息
     */
    void updateAboutMe(AboutMeBean aboutMe);
}
