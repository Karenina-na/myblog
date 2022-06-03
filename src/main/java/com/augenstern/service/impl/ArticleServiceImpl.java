package com.augenstern.service.impl;

import com.augenstern.dao.ArticleDao;
import com.augenstern.domain.AboutMe;
import com.augenstern.domain.ArticleBean;
import com.augenstern.domain.Code;
import com.augenstern.domain.ServerArticleResultBean;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.SystemException;
import com.augenstern.service.ArticleService;
import com.augenstern.service.Util.TagUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Override
    public ServerArticleResultBean SelectArticleByPage(int page) {
        PageHelper.startPage(page, 6);
        List<ArticleBean> articleBeans = articleDao.selectAllArticle();
        int total = articleDao.selectAllArticleNumber();
        return new ServerArticleResultBean(articleBeans, total);
    }

    @Override
    public ServerArticleResultBean SelectArticleByType(int page, String name) throws BusinessException, SystemException {
        if (TagUtil.CheckTagsName(name)) {
            PageHelper.startPage(page, 6);
            List<ArticleBean> articleBeans = articleDao.selectArticleByType(name);
            int total = articleDao.selectArticleByTypeNumber(name);
            return new ServerArticleResultBean(articleBeans, total);
        } else {
            throw new BusinessException("非法字符注入", Code.BUSINESS_ERR);
        }
    }

    @Override
    public ArticleBean SelectArticleById(int id) {
        return articleDao.selectArticleById(id);
    }

    @Override
    public ServerArticleResultBean SelectArticleByName(int page, String name) {
        PageHelper.startPage(page, 6);
        List<ArticleBean> articleBeans = articleDao.selectArticleByName("%" + name + "%");
        int total = articleDao.selectArticleByNameNumber(name);
        return new ServerArticleResultBean(articleBeans,total);
    }

    @Override
    public AboutMe SelectAboutMe() {
        return articleDao.selectAboutMe();
    }
}
