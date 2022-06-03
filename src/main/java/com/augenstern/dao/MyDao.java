package com.augenstern.dao;

import com.augenstern.domain.AboutMe;
import com.augenstern.domain.ArticleBean;
import com.augenstern.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 操作数据库层
 */
@CacheNamespaceRef(name = "com.augenstern.dao.Cache")
public interface MyDao {

    /**
     * 增加标签
     */
    void addTags(@Param("id")int id, @Param("tags")List<String> tags);

    /**
     * id删除标签
     */
    @Delete("delete from ArtMapTag where a_id=#{id}")
    void deleteTagsByArticleId(int id);

    /**
     * 增
     */
    @Insert("insert into Article(date, title, author, messages) " +
            "values (#{date},#{title},#{author},#{messages})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void addArticle(ArticleBean articleBean);

    /**
     * 删
     */
    @Delete("delete from Article where id=#{id}")
    void deleteArticle(int id);

    /**
     * 改
     */
    void updateArticle(ArticleBean articleBean);

    /**
     * 查询管理账号密码
     */
    @Select("select * from User")
    @Options(useCache = false)
    List<User> selectRoot();

    /**
     * 更改个人信息
     */
    void updateAboutMe(AboutMe aboutMe);
}
