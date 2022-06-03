package com.augenstern.service;

import com.augenstern.domain.AboutMe;
import com.augenstern.domain.ArticleBean;
import com.augenstern.exception.SystemException;
import org.springframework.transaction.annotation.Transactional;

/**
 * 操作业务层
 */
public interface MyService {

    /**
     * 增加文章
     *
     * @return true为成功  false为异常
     */
    @Transactional(timeout = 5,rollbackFor = Exception.class)
    boolean AddArticle(ArticleBean articleBean) throws SystemException;

    /**
     * 删除文章
     * @return  ture为成功 false为异常
     */
    @Transactional(timeout = 5, rollbackFor = Exception.class)
    boolean DeleteArticle(ArticleBean articleBean) throws SystemException;

    /**
     * 修改文章
     *
     * @return true为成功 false为异常
     */
    @Transactional(timeout = 5, rollbackFor = Exception.class)
    boolean UpdateArticle(ArticleBean articleBean) throws SystemException;

    /**
     * 查询root账号
     * @return true为存在 false不存在
     */
    @Transactional(timeout = 5, rollbackFor = Exception.class, readOnly = true)
   boolean SelectRoot(String username,String password);

    /**
     * 更改个人信息
     * @return true为成功 false失败
     */
    @Transactional(timeout = 5, rollbackFor = Exception.class)
    boolean UpdateAboutMe(AboutMe aboutMe);
}
