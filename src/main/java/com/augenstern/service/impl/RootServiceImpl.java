package com.augenstern.service.impl;

import com.augenstern.dao.ArticleDao;
import com.augenstern.dao.MyDao;
import com.augenstern.entity.Code;
import com.augenstern.entity.dao.AboutMeBean;
import com.augenstern.entity.dao.UserBean;
import com.augenstern.entity.server.Article;
import com.augenstern.exception.SystemException;
import com.augenstern.service.RootService;
import com.augenstern.service.Util.TagUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RootServiceImpl implements RootService {
    @Resource
    private MyDao myDao;

    @Resource
    private ArticleDao articleDao;

    @Override
    public boolean AddArticle(Article article) throws SystemException {
        myDao.addArticle(article);
        int id = article.getId();
        List<String> tags = article.getTags();
        if (tags != null) {
            boolean result = TagUtil.ChangeTagsId(tags);
            if (result) {
                myDao.addTags(id, tags);
                return true;
            } else {
                throw new SystemException("Tag类型转换数据库id异常", Code.SAVE_ERR);
            }
        } else {
            return true;
        }
    }

    @Override
    public boolean DeleteArticle(Article article) {
        int id = article.getId();
        myDao.deleteTagsByArticleId(id);
        myDao.deleteArticle(id);
        return true;
    }

    @Override
    public boolean UpdateArticle(Article article) {
        Article articleBeanSelect = articleDao.selectArticleById(article.getId());
        if (articleBeanSelect.getDate().equals(article.getDate())) article.setDate(null);
        if (articleBeanSelect.getTitle().equals(article.getTitle())) article.setTitle(null);
        if (articleBeanSelect.getAuthor().equals(article.getAuthor())) article.setAuthor(null);
        if (articleBeanSelect.getMessages().equals(article.getMessages())) article.setMessages(null);
        if (article.getTitle() != null || article.getMessages() != null ||
                article.getAuthor() != null || article.getDate() != null) {
            myDao.updateArticle(article);
        }
        //检查标签更改
        boolean flag = false;
        for (String tag : article.getTags()) {
            if (!articleBeanSelect.getTags().contains(tag)) {
                flag = true;
                break;
            }
        }
        for (String tag : articleBeanSelect.getTags()) {
            if (!article.getTags().contains(tag)) {
                flag = true;
                break;
            }
        }
        //判空
        if (article.getTags().isEmpty()) {
            myDao.deleteTagsByArticleId(article.getId());
        } else {
            //改标签
            if (flag) {
                boolean result = TagUtil.ChangeTagsId(article.getTags());
                if (result) {
                    myDao.deleteTagsByArticleId(article.getId());
                    myDao.addTags(article.getId(), article.getTags());
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean SelectRoot(String username,String password) {
        List<UserBean> roots =  myDao.selectRoot();
        for (UserBean root : roots) {
            if (root.getUsername().equals(username) && root.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean UpdateAboutMe(AboutMeBean aboutMeBean) {
        myDao.updateAboutMe(aboutMeBean);
        return true;
    }
}
