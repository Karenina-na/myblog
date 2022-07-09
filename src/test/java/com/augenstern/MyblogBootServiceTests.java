package com.augenstern;

import com.augenstern.entity.dao.AboutMeBean;
import com.augenstern.entity.server.Article;
import com.augenstern.entity.server.ArticlesResult;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.SystemException;
import com.augenstern.service.Consumer;
import com.augenstern.service.RootService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyblogBootServiceTests {

    @Resource
    Consumer consumer;

    /**
     * 总查询
     */
    @Test
    public void SelectArticleByPageTest(){
        ArticlesResult articleResults = consumer.SelectArticleByPage(1);
        System.out.println(articleResults);
    }

    /**
     * 分页类型查询
     * @throws BusinessException
     * @throws SystemException
     */
    @Test
    public void SelectArticleByTypeTest() throws BusinessException, SystemException {
        ArticlesResult articleResults = consumer.SelectArticleByType(1, "python");
        System.out.println(articleResults);
    }

    /**
     * id查询
     */
    @Test
    public void SelectArticleByIdTest(){
        Article article = consumer.SelectArticleById(6);
        System.out.println(article);
    }

    /**
     * 模糊查询
     */
    @Test
    public void SelectArticleByNameTest(){
        ArticlesResult articleResults = consumer.SelectArticleByName(1, "输");
        System.out.println(articleResults);
    }

    /**
     * 查询必要数据
     */
    @Test
    public void SelectAboutMeTest(){
        AboutMeBean aboutMeBean = consumer.SelectAboutMe();
        System.out.println(aboutMeBean);
    }


    @Resource
    RootService rootService;

    /**
     * 增加
     * @throws SystemException
     */
    @Test
    public void addArticleTest() throws SystemException {
        Article article = new Article();
        article.setMessages("信息");
        article.setTitle("1");
        article.setAuthor("作者");
        article.setDate("2022-1-1");
        rootService.AddArticle(article);
        ArticlesResult articlesResult = consumer.SelectArticleByName(1, "1");
        System.out.println(articlesResult);
    }


    /**
     * 删除
     * @throws SystemException
     */
    @Test
    public void deleteArticleTest() throws SystemException {
        Article article = new Article();
        article.setId(8);
        rootService.DeleteArticle(article);
        ArticlesResult articlesResult = consumer.SelectArticleByName(1, "1");
        System.out.println(articlesResult);
    }

    /**
     * 修改
     * @throws SystemException
     */
    @Test
    public void updateArticleTest() throws SystemException {
        Article article = new Article();
        article.setMessages("信息");
        article.setTitle("1");
        article.setAuthor("作者");
        article.setDate("2022-1-1");
        List<String> tags=new ArrayList<>();
        tags.add("java");
        article.setTags(tags);
        rootService.AddArticle(article);
        ArticlesResult articlesResult1 = consumer.SelectArticleByName(1, "1");
        System.out.println(articlesResult1);

        article.setMessages("修改");
        article.setId(articlesResult1.getArticles().get(0).getId());
        rootService.UpdateArticle(article);
        ArticlesResult articlesResult2 = consumer.SelectArticleByName(1, "1");
        System.out.println(articlesResult2);
    }

    /**
     * 查询管理账号
     */
    @Test
    public void SelectRootTest(){
        boolean root = rootService.SelectRoot("123", "123");
        System.out.println(root);
    }

    /**
     * 修改个人信息
     */
    @Test
    public void updateAboutMeTest(){
        AboutMeBean aboutMe = new AboutMeBean();
        aboutMe.setAuthor("a");
        aboutMe.setIntroduce("b");
        aboutMe.setNotice("c");
        rootService.UpdateAboutMe(aboutMe);
        System.out.println(consumer.SelectAboutMe());
    }
}
