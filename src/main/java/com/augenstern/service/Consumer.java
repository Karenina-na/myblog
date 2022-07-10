package com.augenstern.service;


import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.augenstern.entity.dao.AboutMeBean;
import com.augenstern.entity.server.Article;
import com.augenstern.entity.server.ArticlesResult;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.SystemException;
import org.springframework.transaction.annotation.Transactional;

/**
 * 查询业务层
 */
public interface Consumer {

    /**
     * 总查询
     *
     */
    @Transactional(timeout = 5, readOnly = true, rollbackFor = Exception.class)
    ArticlesResult SelectArticleByPage(int page);

    /**
     * 按类型查询
     *
     */
    @Transactional(timeout = 5, readOnly = true, rollbackFor = Exception.class)
    ArticlesResult SelectArticleByType(int page, String type) throws BusinessException, SystemException;

    /**
     * 按指定id查询
     *
     */
    @Transactional(timeout = 5, readOnly = true, rollbackFor = Exception.class)
    Article SelectArticleById(int id);

    /**
     * 按模糊字段查询
     *
     */
    @Transactional(timeout = 5, readOnly = true, rollbackFor = Exception.class)
    ArticlesResult SelectArticleByName(int page,String name);

    /**
     * 查询必要数据
     */
    @Transactional(timeout = 5, readOnly = true, rollbackFor = Exception.class)
    AboutMeBean SelectAboutMe();
}
