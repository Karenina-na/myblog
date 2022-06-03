package com.augenstern.dao;

import com.augenstern.domain.AboutMe;
import com.augenstern.domain.ArticleBean;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 文章查询数据库层
 */
@CacheNamespaceRef(name = "com.augenstern.dao.Cache")
public interface ArticleDao {

    /**
     * 查询全部
     */
    @Select("select Article.id,Article.title,Article.author,Article.date,left(Article.messages,140) messages " +
            " from Article order by Article.id desc")
    @ResultMap("tagFind")
    List<ArticleBean> selectAllArticle();

    @Select("select count(Article.id) from Article ")
    int selectAllArticleNumber();

    /**
     * 类型查询
     */
    @Select("select Article.id,Article.title,Article.author,Article.date,left(Article.messages,140) messages " +
            "from Article,ArtMapTag,Tag " +
            "where Article.id=ArtMapTag.a_id and " +
            "Tag.id=ArtMapTag.t_id and " +
            "Tag.tag=#{tag} group by Article.id order by Article.id desc")
    @ResultMap("tagFind")
    List<ArticleBean> selectArticleByType(String tag);

    @Select("select count(Article.id) from Article,ArtMapTag,Tag " +
            "where Article.id=ArtMapTag.a_id and Tag.id=ArtMapTag.t_id and Tag.tag=#{tag}")
    int selectArticleByTypeNumber(String tag);

    /**
     * 查询指定id文章
     */
    @Select("select * from Article where id=#{id}")
    @ResultMap("tagFind")
    ArticleBean selectArticleById(int id);

    /**
     * 模糊查询文章
     */
    @Select("select Article.id,Article.title,Article.author,Article.date,left(Article.messages,140) messages " +
            " from Article " +
            "where Article.title like #{name} order by Article.id desc")
    @ResultMap("tagFind")
    List<ArticleBean> selectArticleByName(String name);

    @Select("select count(Article.id) from Article " +
            "where Article.title like #{name}")
    int selectArticleByNameNumber(String name);

    /**
     * 查询必要数据
     */
    @Select("select author,introduce,notice from AboutMe")
    AboutMe selectAboutMe();
}
