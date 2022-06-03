package com.augenstern.controller.util;

import com.augenstern.domain.ArticleBean;
import com.augenstern.domain.ServerArticleResultBean;

import java.util.List;

/**
 * 封装转换代码
 */
public class ArticleChange {
    /**
     * 后端转前端
     *
     * @param article
     */
    public static void BackChangeFront(ArticleBean article) {
        article.setId(IdChange.BackToFront(article.getId()));
    }

    public static void BackChangeFront(ServerArticleResultBean articlesBean) {
        List<ArticleBean> articles = articlesBean.getArticleBean();
        for (ArticleBean article : articles) {
            article.setId(IdChange.BackToFront(article.getId()));
        }
    }

    /**
     * 前端转后端
     *
     * @param article
     */
    public static void FrontChangeBack(ArticleBean article) {
        article.setId(IdChange.FrontToBack(article.getId()));
    }

    public static int FrontChangeBack(Integer id) {
        return IdChange.FrontToBack(id);
    }
}
