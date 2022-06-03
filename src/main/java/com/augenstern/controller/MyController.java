package com.augenstern.controller;

import com.augenstern.controller.util.ArticleChange;
import com.augenstern.domain.ArticleBean;
import com.augenstern.domain.Code;
import com.augenstern.domain.ResultBean;
import com.augenstern.domain.User;
import com.augenstern.exception.SystemException;
import com.augenstern.service.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/rootManager")
public class MyController {

    @Resource
    private MyService myService;

    /**
     * 增
     */
    @PostMapping("/Article")
    @ResponseBody
    public ResultBean AddArticle(@RequestBody ArticleBean articleBean) throws SystemException {
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
    @ResponseBody
    public ResultBean DeleteArticle(@RequestBody ArticleBean articleBean) throws SystemException {
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
    @ResponseBody
    public ResultBean UpdateArticle(@RequestBody ArticleBean articleBean) throws SystemException {
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
    @ResponseBody
    public ResultBean GetRootManager(@RequestBody User user) {

        boolean result = myService.SelectRoot(user.getUsername(), user.getPassword());
        if (result) {
            return new ResultBean(true, Code.GET_OK);
        } else {
            return new ResultBean(false, Code.GET_ERR, "用户名密码错误");
        }
    }
}
