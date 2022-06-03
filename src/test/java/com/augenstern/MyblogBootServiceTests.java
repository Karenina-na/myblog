package com.augenstern;

import com.augenstern.domain.AboutMe;
import com.augenstern.domain.ArticleBean;
import com.augenstern.domain.ServerArticleResultBean;
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
        ServerArticleResultBean serverArticleResultBean = articleService.SelectArticleByPage(1);
        System.out.println(serverArticleResultBean);
    }

    /**
     * 分页类型查询
     * @throws BusinessException
     * @throws SystemException
     */
    @Test
    public void SelectArticleByTypeTest() throws BusinessException, SystemException {
        ServerArticleResultBean serverArticleResultBean = articleService.SelectArticleByType(1, "python");
        System.out.println(serverArticleResultBean);
    }

    /**
     * id查询
     */
    @Test
    public void SelectArticleByIdTest(){
        ArticleBean articleBean = articleService.SelectArticleById(1);
        System.out.println(articleBean);
    }

    /**
     * 模糊查询
     */
    @Test
    public void SelectArticleByNameTest(){
        ServerArticleResultBean serverArticleResultBean = articleService.SelectArticleByName(1, "输");
        System.out.println(serverArticleResultBean);
    }

    /**
     * 查询必要数据
     */
    @Test
    public void SelectAboutMeTest(){
        AboutMe aboutMe = articleService.SelectAboutMe();
        System.out.println(aboutMe);
    }


    @Resource
    MyService myService;

    /**
     * 增加
     * @throws SystemException
     */
    @Test
    public void addArticleTest() throws SystemException {
        ArticleBean articleBean = new ArticleBean();
        articleBean.setMessages("信息");
        articleBean.setTitle("1");
        articleBean.setAuthor("作者");
        articleBean.setDate("2022-1-1");
        myService.AddArticle(articleBean);
        ServerArticleResultBean serverArticleResultBean = articleService.SelectArticleByName(1, "1");
        System.out.println(serverArticleResultBean);
    }


    /**
     * 删除
     * @throws SystemException
     */
    @Test
    public void deleteArticleTest() throws SystemException {
        ArticleBean articleBean = new ArticleBean();
        articleBean.setId(19);
        myService.DeleteArticle(articleBean);
        ServerArticleResultBean serverArticleResultBean = articleService.SelectArticleByName(1, "1");
        System.out.println(serverArticleResultBean);
    }

    /**
     * 修改
     * @throws SystemException
     */
    @Test
    public void updateArticleTest() throws SystemException {
        ArticleBean articleBean = new ArticleBean();
        articleBean.setMessages("信息");
        articleBean.setTitle("1");
        articleBean.setAuthor("作者");
        articleBean.setDate("2022-1-1");
        List<String> tags=new ArrayList<>();
        tags.add("java");
        articleBean.setTags(tags);
        myService.AddArticle(articleBean);
        ServerArticleResultBean serverArticleResultBean1 = articleService.SelectArticleByName(1, "1");
        System.out.println(serverArticleResultBean1);

        articleBean.setMessages("修改");
        articleBean.setId(serverArticleResultBean1.getArticleBean().get(0).getId());
        myService.UpdateArticle(articleBean);
        ServerArticleResultBean serverArticleResultBean2 = articleService.SelectArticleByName(1, "1");
        System.out.println(serverArticleResultBean2);
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
        AboutMe aboutMe = new AboutMe();
        aboutMe.setAuthor("a");
        aboutMe.setIntroduce("b");
        aboutMe.setNotice("c");
        myService.UpdateAboutMe(aboutMe);
        System.out.println(articleService.SelectAboutMe());
    }
}
