package com.augenstern.service.impl;

import com.augenstern.dao.ArticleDao;
import com.augenstern.dao.MyDao;
import com.augenstern.domain.ArticleBean;
import com.augenstern.domain.Code;
import com.augenstern.domain.User;
import com.augenstern.exception.SystemException;
import com.augenstern.service.MyService;
import com.augenstern.service.Util.TagUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyServiceImpl implements MyService {
    @Resource
    private MyDao myDao;

    @Resource
    private ArticleDao articleDao;

    @Override
    public boolean AddArticle(ArticleBean articleBean) throws SystemException {
        myDao.addArticle(articleBean);
        int id = articleBean.getId();
        List<String> tags = articleBean.getTags();
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
    public boolean DeleteArticle(ArticleBean articleBean) {
        int id = articleBean.getId();
        myDao.deleteTagsByArticleId(id);
        myDao.deleteArticle(id);
        return true;
    }

    @Override
    public boolean UpdateArticle(ArticleBean articleBean) {
        ArticleBean articleBeanSelect = articleDao.selectArticleById(articleBean.getId());
        if (articleBeanSelect.getDate().equals(articleBean.getDate())) articleBean.setDate(null);
        if (articleBeanSelect.getTitle().equals(articleBean.getTitle())) articleBean.setTitle(null);
        if (articleBeanSelect.getAuthor().equals(articleBean.getAuthor())) articleBean.setAuthor(null);
        if (articleBeanSelect.getMessages().equals(articleBean.getMessages())) articleBean.setMessages(null);
        if (articleBean.getTitle() != null || articleBean.getMessages() != null ||
                articleBean.getAuthor() != null || articleBean.getDate() != null) {
            myDao.updateArticle(articleBean);
        }
        //检查标签更改
        boolean flag = false;
        for (String tag : articleBean.getTags()) {
            if (!articleBeanSelect.getTags().contains(tag)) {
                flag = true;
                break;
            }
        }
        for (String tag : articleBeanSelect.getTags()) {
            if (!articleBean.getTags().contains(tag)) {
                flag = true;
                break;
            }
        }
        //判空
        if (articleBean.getTags().isEmpty()) {
            myDao.deleteTagsByArticleId(articleBean.getId());
        } else {
            //改标签
            if (flag) {
                boolean result = TagUtil.ChangeTagsId(articleBean.getTags());
                if (result) {
                    myDao.deleteTagsByArticleId(articleBean.getId());
                    myDao.addTags(articleBean.getId(), articleBean.getTags());
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
        List<User> roots =  myDao.selectRoot();
        for (User root : roots) {
            if (root.getUsername().equals(username) && root.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
