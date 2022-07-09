package com.augenstern;

import com.augenstern.entity.dao.AboutMeBean;
import com.augenstern.entity.server.Article;
import com.augenstern.entity.server.ArticlesResult;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.SystemException;
import com.augenstern.service.ArticleService;
import com.augenstern.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyblogBootServiceTests {

    @Resource
    ArticleService articleService;

    /**
     * 总查询
     */
    @Test
    public void SelectArticleByPageTest(){
        ArticlesResult articleResults = articleService.SelectArticleByPage(1);
        System.out.println(articleResults);
    }

    /**
     * 分页类型查询
     * @throws BusinessException
     * @throws SystemException
     */
    @Test
    public void SelectArticleByTypeTest() throws BusinessException, SystemException {
        ArticlesResult articleResults = articleService.SelectArticleByType(1, "python");
        System.out.println(articleResults);
    }

    /**
     * id查询
     */
    @Test
    public void SelectArticleByIdTest(){
        Article article = articleService.SelectArticleById(6);
        System.out.println(article);
    }

    /**
     * 模糊查询
     */
    @Test
    public void SelectArticleByNameTest(){
        ArticlesResult articleResults = articleService.SelectArticleByName(1, "输");
        System.out.println(articleResults);
    }

    /**
     * 查询必要数据
     */
    @Test
    public void SelectAboutMeTest(){
        AboutMeBean aboutMeBean = articleService.SelectAboutMe();
        System.out.println(aboutMeBean);
    }


    @Resource
    MyService myService;

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
        myService.AddArticle(article);
        ArticlesResult articlesResult = articleService.SelectArticleByName(1, "1");
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
        myService.DeleteArticle(article);
        ArticlesResult articlesResult = articleService.SelectArticleByName(1, "1");
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
        myService.AddArticle(article);
        ArticlesResult articlesResult1 = articleService.SelectArticleByName(1, "1");
        System.out.println(articlesResult1);

        article.setMessages("修改");
        article.setId(articlesResult1.getArticles().get(0).getId());
        myService.UpdateArticle(article);
        ArticlesResult articlesResult2 = articleService.SelectArticleByName(1, "1");
        System.out.println(articlesResult2);
    }

    /**
     * 查询管理账号
     */
    @Test
    public void SelectRootTest(){
        boolean root = myService.SelectRoot("123", "123");
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
        myService.UpdateAboutMe(aboutMe);
        System.out.println(articleService.SelectAboutMe());
    }
}
