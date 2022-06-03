package com.augenstern.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 全局拦截器
 */
@Component
public class AllProjectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String[] split = request.getRequestURI().split("/");
        if (split.length>0){
            switch (split[1]){
                case "about":
                case "articles":
                case "article":{
                    //查询匹配签名
                    String token= request.getHeader("Req");
                    final String sign= "1011011011011";
                    return sign.equals(token);
                }
                default:{
                    return true;
                }
            }
        }
        else return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
