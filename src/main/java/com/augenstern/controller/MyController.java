package com.augenstern.controller;

import com.augenstern.controller.util.ArticleChange;
import com.augenstern.entity.*;
import com.augenstern.entity.controller.ResultBean;
import com.augenstern.entity.dao.AboutMeBean;
import com.augenstern.entity.dao.UserBean;
import com.augenstern.entity.server.Article;
import com.augenstern.exception.BusinessException;
import com.augenstern.exception.SystemException;
import com.augenstern.service.MyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 管理控制器
 */
@RestController
@RequestMapping("/rootManager")
@Api(tags ="管理员控制器")
public class MyController {

    @Resource
    private MyService myService;

    /**
     * 增
     */
    @PostMapping("/Article")
    @ApiOperation("增加文章")
    public ResultBean AddArticle(@ApiParam("ArticleBean 文章类") @RequestBody Article articleBean) throws SystemException {
        boolean result = myService.AddArticle(articleBean);
        if (result) {
            return new ResultBean(true, Code.SAVE_OK);
        } else {
            return new ResultBean(false, Code.SAVE_ERR, "保存错误");
        }
    }

    /**
     * 删
     */
    @DeleteMapping("/Article")
    @ApiOperation("删除文章")
    public ResultBean DeleteArticle(@ApiParam("ArticleBean 文章类") @RequestBody Article articleBean) throws SystemException, BusinessException {
        ArticleChange.FrontChangeBack(articleBean);
        boolean result = myService.DeleteArticle(articleBean);
        if (result) {
            return new ResultBean(true, Code.DELETE_OK);
        } else {
            return new ResultBean(false, Code.DELETE_ERR, "删除错误");
        }
    }

    /**
     * 改
     */
    @PutMapping("/Article")
    @ApiOperation("更改文章")
    public ResultBean UpdateArticle(@ApiParam("ArticleBean 文章类") @RequestBody Article articleBean) throws SystemException, BusinessException {
        ArticleChange.FrontChangeBack(articleBean);
        boolean result = myService.UpdateArticle(articleBean);
        if (result) {
            return new ResultBean(true, Code.UPDATE_OK);
        } else {
            return new ResultBean(false, Code.UPDATE_ERR, "更新错误");
        }
    }

    /**
     * 查询用户名密码
     */
    @PostMapping("")
    @ApiOperation("查询管理账号密码")
    public ResultBean GetRootManager(@ApiParam("User 用户类") @RequestBody UserBean user) {

        boolean result = myService.SelectRoot(user.getUsername(), user.getPassword());
        if (result) {
            return new ResultBean(true, Code.GET_OK);
        } else {
            return new ResultBean(false, Code.GET_ERR, "用户名密码错误");
        }
    }

    /**
     * 更改个人信息
     */
    @PutMapping("/About")
    @ApiOperation("更改个人信息")
    public ResultBean UpdateAboutMe(@ApiParam("AboutMe aboutMe类") @RequestBody AboutMeBean aboutMe){
        boolean result = myService.UpdateAboutMe(aboutMe);
        if (result){
            return new ResultBean(true,Code.UPDATE_OK);
        }
        else{
            return new ResultBean(false,Code.UPDATE_ERR,"更改失败");
        }
    }
}
