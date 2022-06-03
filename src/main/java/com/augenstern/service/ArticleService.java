package com.augenstern.service;

import com.augenstern.domain.AboutMe;
import com.augenstern.domain.ArticleBean;
import com.augenstern.domain.ServerArticleResultBean;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.SystemException;
import org.springframework.transaction.annotation.Transactional;

/**
 * 查询业务层
 */
public interface ArticleService {

    /**
     * 总查询
     *
     */
    @Transactional(timeout = 5, readOnly = true, rollbackFor = Exception.class)
    ServerArticleResultBean SelectArticleByPage(int page);

    /**
     * 按类型查询
     *
     */
    @Transactional(timeout = 5, readOnly = true, rollbackFor = Exception.class)
    ServerArticleResultBean SelectArticleByType(int page, String type) throws BusinessException, SystemException;

    /**
     * 按指定id查询
     *
     */
    @Transactional(timeout = 5, readOnly = true, rollbackFor = Exception.class)
    ArticleBean SelectArticleById(int id);

    /**
     * 按模糊字段查询
     *
     */
    @Transactional(timeout = 5, readOnly = true, rollbackFor = Exception.class)
    ServerArticleResultBean SelectArticleByName(int page,String name);

    /**
     * 查询必要数据
     */
    @Transactional(timeout = 5, readOnly = true, rollbackFor = Exception.class)
    AboutMe SelectAboutMe();
}
