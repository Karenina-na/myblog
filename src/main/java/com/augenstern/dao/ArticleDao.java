package com.augenstern.dao;

import com.augenstern.entity.dao.AboutMeBean;
import com.augenstern.entity.server.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

/**
 * 文章查询数据库层
 */
@CacheNamespaceRef(name = "com.augenstern.dao.Cache")
@Mapper
public interface ArticleDao extends BaseMapper<Article> {

    /**
     * 查询全部
     */
    @Select("select article.id,article.title,article.author,article.date,left(article.messages,140) messages " +
            " from article order by article.id desc")
    @ResultMap("tagFind")
    Page<Article> selectAllArticle(@Param("page")Page<Article> page);

    @Select("select count(article.id) from article ")
    int selectAllArticleNumber();

    /**
     * 类型查询
     */
    @Select("select article.id,article.title,article.author,article.date,left(article.messages,140) messages " +
            "from article,artmaptag,tag " +
            "where article.id=artmaptag.a_id and " +
            "tag.id=artmaptag.t_id and " +
            "tag.tag=#{tag} group by article.id order by article.id desc")
    @ResultMap("tagFind")
    Page<Article> selectArticleByType(@Param("page")Page<Article>page,@Param("tag")String tag);

    @Select("select count(article.id) from article,artmaptag,tag " +
            "where article.id=artmaptag.a_id and tag.id=artmaptag.t_id and tag.tag=#{tag}")
    int selectArticleByTypeNumber(String tag);

    /**
     * 查询指定id文章
     */
    @Select("select * from article where id=#{id}")
    @ResultMap("tagFind")
    Article selectArticleById(int id);

    /**
     * 模糊查询文章
     */
    @Select("select article.id,article.title,article.author,article.date,left(article.messages,140) messages " +
            " from article " +
            "where article.title like #{name} order by article.id desc")
    @ResultMap("tagFind")
    Page<Article> selectArticleByName(@Param("page")Page<Article>page,@Param("name")String name);

    @Select("select count(article.id) from article " +
            "where article.title like #{name}")
    int selectArticleByNameNumber(String name);

    /**
     * 查询必要数据
     */
    @Select("select author,introduce,notice from aboutme")
    AboutMeBean selectAboutMe();
}
