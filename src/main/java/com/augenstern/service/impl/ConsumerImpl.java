package com.augenstern.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.augenstern.dao.ArticleDao;
import com.augenstern.entity.Code;
import com.augenstern.entity.dao.AboutMeBean;
import com.augenstern.entity.server.Article;
import com.augenstern.entity.server.ArticlesResult;
import com.augenstern.exception.BusinessException;
import com.augenstern.service.Consumer;
import com.augenstern.service.Util.TagUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConsumerImpl implements Consumer {

    /**
     * 文章缓存
     */
    @CreateCache(name = "articleCache-", expire = 3600, cacheType = CacheType.REMOTE)
    private Cache<Long, Article> articleCache;

    /**
     * 个人信息缓存
     */
    @CreateCache(name = "aboutMeCache", expire = 3600, cacheType = CacheType.REMOTE)
    private Cache<Long, AboutMeBean> aboutMeCache;

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
        Article article = articleCache.get((long) id);
        if (article == null) {
            article = articleDao.selectArticleById(id);
            articleCache.put(Long.valueOf(article.getId()),article);
        }
        return article;
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
        AboutMeBean aboutMe = aboutMeCache.get(null);
        if (aboutMe == null) {
            aboutMe = articleDao.selectAboutMe();
        }
        return aboutMe;
    }
}
