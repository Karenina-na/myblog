package com.augenstern.service.impl;

import com.augenstern.dao.ArticleDao;
import com.augenstern.entity.Code;
import com.augenstern.entity.dao.AboutMeBean;
import com.augenstern.entity.server.Article;
import com.augenstern.entity.server.ArticlesResult;
import com.augenstern.exception.BusinessException;
import com.augenstern.service.ArticleService;
import com.augenstern.service.Util.TagUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Override
    public ArticlesResult SelectArticleByPage(int page) {
        Page<Article> Page=new Page<>(page,6);
        List<Article> articles = articleDao.selectAllArticle(Page).getRecords();
        int total = articleDao.selectAllArticleNumber();
        return new ArticlesResult(articles, total);
    }

    @Override
    public ArticlesResult SelectArticleByType(int page, String name) throws BusinessException {
        if (TagUtil.CheckTagsName(name)) {
            Page<Article> Page=new Page<>(page,6);
            List<Article> articleBeans = articleDao.selectArticleByType(Page,name).getRecords();
            int total = articleDao.selectArticleByTypeNumber(name);
            return new ArticlesResult(articleBeans, total);
        } else {
            throw new BusinessException("非法字符注入", Code.BUSINESS_ERR);
        }
    }

    @Override
    public Article SelectArticleById(int id) {
        return articleDao.selectArticleById(id);
    }

    @Override
    public ArticlesResult SelectArticleByName(int page, String name) {
        Page<Article> Page=new Page<>(page,6);
        List<Article> articles = articleDao.selectArticleByName(Page,"%" + name + "%").getRecords();
        int total = articleDao.selectArticleByNameNumber(name);
        return new ArticlesResult(articles, total);
    }

    @Override
    public AboutMeBean SelectAboutMe() {
        return articleDao.selectAboutMe();
    }
}
