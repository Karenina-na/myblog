package com.augenstern.controller.util;

import com.augenstern.domain.ArticleBean;
import com.augenstern.domain.Code;
import com.augenstern.domain.ServerArticleResultBean;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.SystemException;

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
    public static void BackChangeFront(ArticleBean article) throws SystemException {
        int id = IdChange.BackToFront(article.getId());
        if (id!=-1){
            article.setId(id);
        }
        else{
            throw new SystemException("非法后端转前端id错误,id="+id, Code.SYSTEM_ERR);
        }
    }

    public static void BackChangeFront(ServerArticleResultBean articlesBean) throws SystemException {
        List<ArticleBean> articles = articlesBean.getArticleBean();
        for (ArticleBean article : articles) {
            int id = IdChange.BackToFront(article.getId());
            if (id!=-1){
                article.setId(id);
            }
            else{
                throw new SystemException("非法后端转前端id错误,id="+id, Code.SYSTEM_ERR);
            }
        }
    }

    /**
     * 前端转后端
     *
     * @param article
     */
    public static void FrontChangeBack(ArticleBean article) throws BusinessException {
        int id = IdChange.FrontToBack(article.getId());
        if (id!=-1){
            article.setId(id);
        }
        else{
            throw new BusinessException("非法用户id注入,id="+id, Code.BUSINESS_ERR);
        }
    }

    public static int FrontChangeBack(Integer Id) throws BusinessException {
        int id = IdChange.FrontToBack(Id);
        if (id!=-1){
            return id;
        }
        else{
            throw new BusinessException("非法用户id注入,id="+id, Code.BUSINESS_ERR);
        }
    }
}
