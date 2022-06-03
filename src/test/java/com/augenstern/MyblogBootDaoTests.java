package com.augenstern;

import com.augenstern.dao.ArticleDao;
import com.augenstern.dao.MyDao;
import com.augenstern.domain.AboutMe;
import com.augenstern.domain.ArticleBean;
import com.augenstern.domain.User;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyblogBootDaoTests {

    @Resource
    ArticleDao articleDao;

    /**
     * 测试分页助手
     */
    @Test
    public void pageHelperTest() {
        PageHelper.startPage(1, 1);
        List<ArticleBean> articleBeans = articleDao.selectAllArticle();
        System.out.println(articleBeans);
    }

    /**
     * 测试查询全部
     */
    @Test
    public void selectAllArticleTest() {
        List<ArticleBean> articleBeans = articleDao.selectAllArticle();
        System.out.println(articleBeans);
    }

    @Test
    public void selectAllArticleNumberTest() {
        int number = articleDao.selectAllArticleNumber();
        System.out.println(number);
    }

    /**
     * 类型查询
     */
    @Test
    public void selectArticleByTypeTest() {
        List<ArticleBean> articleBeans = articleDao.selectArticleByType("java");
        System.out.println(articleBeans);
    }

    @Test
    public void selectArticleByTypeNumberTest() {
        int number = articleDao.selectArticleByTypeNumber("java");
        System.out.println(number);
    }

    /**
     * 查询文章id
     */
    @Test
    public void selectArticleByIdTest() {
        ArticleBean articleBean = articleDao.selectArticleById(1);
        System.out.println(articleBean);
    }

    /**
     * 模糊查询
     */
    @Test
    public void selectArticleByNameTest() {
        List<ArticleBean> articleBeans = articleDao.selectArticleByName("%请%");
        System.out.println(articleBeans);
    }

    @Test
    public void selectArticleByNameNumberTest() {
        int number = articleDao.selectArticleByNameNumber("%请%");
        System.out.println(number);
    }

    /**
     * 查询必要数据
     */
    @Test
    public void selectAboutMeTest() {
        AboutMe aboutMe = articleDao.selectAboutMe();
        System.out.println(aboutMe);
    }


    @Resource
    MyDao myDao;

    /**
     * 增加标签与删除全部标签
     */
    @Test
    public void AddAndDeleteTagsTest() {
        //添加
        List<String> S = new ArrayList<>();
        S.add("1");
        myDao.addTags(1, S);
        ArticleBean articleBean1 = articleDao.selectArticleById(1);
        System.out.println(articleBean1);

        //删除
        myDao.deleteTagsByArticleId(1);
        ArticleBean articleBean2 = articleDao.selectArticleById(1);
        System.out.println(articleBean2);
    }

    /**
     * 增加
     */
    @Test
    public void addArticleTest() {
        ArticleBean articleBean = new ArticleBean();
        articleBean.setMessages("信息");
        articleBean.setTitle("1");
        articleBean.setAuthor("作者");
        articleBean.setDate("2022-1-1");
        myDao.addArticle(articleBean);
        List<ArticleBean> articleBeans = articleDao.selectArticleByName("%1%");
        System.out.println(articleBeans);
    }

    /**
     * 删除
     */
    @Test
    public void deleteArticleTest() {
        myDao.deleteArticle(11);
        List<ArticleBean> articleBeans = articleDao.selectArticleByName("%1%");
        System.out.println(articleBeans);
    }

    /**
     * 修改
     */
    @Test
    public void updateArticleTest() {
        ArticleBean articleBean = new ArticleBean();
        articleBean.setMessages("信息");
        articleBean.setTitle("1");
        articleBean.setAuthor("作者");
        articleBean.setDate("2022-1-1");
        myDao.addArticle(articleBean);
        List<ArticleBean> articleBeans1 = articleDao.selectArticleByName("%1%");
        System.out.println(articleBeans1);

        articleBean.setMessages("修改");
        myDao.updateArticle(articleBean);
        List<ArticleBean> articleBeans2 = articleDao.selectArticleByName("%1%");
        System.out.println(articleBeans2);
    }

    /**
     * 查询管理员账号密码
     */
    @Test
    public void selectRootTest(){
        List<User> users = myDao.selectRoot();
        System.out.println(users);
    }

    /**
     * 修改个人信息
     */
    @Test
    public void updateAboutMeTest(){
        AboutMe aboutMe = new AboutMe();
        aboutMe.setAuthor("aaa");
        aboutMe.setIntroduce("bbb");
        aboutMe.setNotice("ccc");
        myDao.updateAboutMe(aboutMe);
        System.out.println(articleDao.selectAboutMe());
    }
}
