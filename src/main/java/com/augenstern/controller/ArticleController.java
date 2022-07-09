package com.augenstern.controller;

import com.augenstern.controller.util.ArticleChange;
import com.augenstern.entity.*;
import com.augenstern.entity.controller.ResultBean;
import com.augenstern.entity.controller.TypeAndPageBean;
import com.augenstern.entity.dao.AboutMeBean;
import com.augenstern.entity.server.Article;
import com.augenstern.entity.server.ArticlesResult;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.SystemException;
import com.augenstern.service.Consumer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 查询控制器
 */
@RestController
@Api(tags ="用户查询控制器")
public class ArticleController {

    @Resource
    private Consumer consumer;

    /**
     * 分页查询
     */
    @ApiOperation("分页查询全部")
    @GetMapping("/articles/{page}")
    public ResultBean SelectArticleByPage(@ApiParam("页码") @PathVariable Integer page) throws SystemException {
        ArticlesResult data = consumer.SelectArticleByPage(page);
        if (data == null) {
            return new ResultBean(null, Code.GET_ERR);
        } else {
            ArticleChange.BackChangeFront(data);
            return new ResultBean(data.getArticles(), Code.GET_OK, data.getTotal());
        }
    }

    /**
     * 类型和页码查询
     */
    @ApiOperation("分页类型查询")
    @PostMapping("/articles")
    public ResultBean SelectArticleByType(@ApiParam("TypeAndPageBean 类型和页码类") @RequestBody TypeAndPageBean TypeAndPage) throws BusinessException, SystemException {
        ArticlesResult data = consumer.SelectArticleByType(
                TypeAndPage.getPage(), TypeAndPage.getType());
        if (data == null) {
            return new ResultBean(null, Code.GET_ERR);
        } else {
            ArticleChange.BackChangeFront(data);
            return new ResultBean(data.getArticles(), Code.GET_OK, data.getTotal());
        }
    }

    /**
     * id查询文章
     */
    @ApiOperation("查询指定id文章")
    @GetMapping("/article/{id}")
    public ResultBean SelectArticleById(@ApiParam("文章id") @PathVariable Integer id) throws BusinessException, SystemException {
        int Id = ArticleChange.FrontChangeBack(id);
        Article data = consumer.SelectArticleById(Id);
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
    @ApiOperation("模糊字段查询")
    @GetMapping("/articles/{name}/{page}")
    public ResultBean SelectArticleByName(@ApiParam("模糊字段") @PathVariable String name, @ApiParam("页码") @PathVariable Integer page) throws SystemException {
        ArticlesResult data = consumer.SelectArticleByName(page, name);
        if (data == null) {
            return new ResultBean(null, Code.GET_ERR);
        } else {
            ArticleChange.BackChangeFront(data);
            return new ResultBean(data.getArticles(), Code.GET_OK,data.getTotal());
        }
    }

    /**
     * 查询必要数据
     */
    @ApiOperation("查询必要数据")
    @GetMapping("/about")
    public ResultBean SelectAboutMethod(){
        AboutMeBean data = consumer.SelectAboutMe();
        if (data == null) {
            return new ResultBean(null, Code.GET_ERR);
        } else {
            return new ResultBean(data, Code.GET_OK);
        }
    }
}
