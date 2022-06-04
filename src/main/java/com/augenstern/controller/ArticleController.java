package com.augenstern.controller;

import com.augenstern.controller.util.ArticleChange;
import com.augenstern.domain.*;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.SystemException;
import com.augenstern.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 分页查询
     */
    @GetMapping("/articles/{page}")
    public ResultBean SelectArticleByPage(@PathVariable Integer page) throws SystemException {
        ServerArticleResultBean data = articleService.SelectArticleByPage(page);
        if (data == null) {
            return new ResultBean(null, Code.GET_ERR);
        } else {
            ArticleChange.BackChangeFront(data);
            return new ResultBean(data.getArticleBean(), Code.GET_OK, data.getTotal());
        }
    }

    /**
     * 类型和页码查询
     */
    @PostMapping("/articles")
    public ResultBean SelectArticleByType(@RequestBody TypeAndPageBean TypeAndPage) throws BusinessException, SystemException {
        ServerArticleResultBean data = articleService.SelectArticleByType(
                TypeAndPage.getPage(), TypeAndPage.getType());
        if (data == null) {
            return new ResultBean(null, Code.GET_ERR);
        } else {
            ArticleChange.BackChangeFront(data);
            return new ResultBean(data.getArticleBean(), Code.GET_OK, data.getTotal());
        }
    }

    /**
     * id查询文章
     */
    @GetMapping("/article/{id}")
    public ResultBean SelectArticleById(@PathVariable Integer id) throws BusinessException, SystemException {
        int Id = ArticleChange.FrontChangeBack(id);
        ArticleBean data = articleService.SelectArticleById(Id);
        if (data == null) {
            return new ResultBean(null, Code.GET_ERR);
        } else {
            ArticleChange.BackChangeFront(data);
            return new ResultBean(data, Code.GET_OK);
        }
    }

    /**
     * 按模糊字段查询
     */
    @GetMapping("/articles/{name}/{page}")
    public ResultBean SelectArticleByName(@PathVariable String name, @PathVariable Integer page) throws SystemException {
        ServerArticleResultBean data = articleService.SelectArticleByName(page, name);
        if (data == null) {
            return new ResultBean(null, Code.GET_ERR);
        } else {
            ArticleChange.BackChangeFront(data);
            return new ResultBean(data.getArticleBean(), Code.GET_OK,data.getTotal());
        }
    }

    /**
     * 查询必要数据
     */
    @GetMapping("/about")
    public ResultBean SelectAboutMethod(){
        AboutMe data = articleService.SelectAboutMe();
        if (data == null) {
            return new ResultBean(null, Code.GET_ERR);
        } else {
            return new ResultBean(data, Code.GET_OK);
        }
    }
}
