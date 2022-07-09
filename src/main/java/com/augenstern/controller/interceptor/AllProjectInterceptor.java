package com.augenstern.controller.interceptor;

import com.augenstern.exception.SystemException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截器
 */
@Component
public class AllProjectInterceptor implements HandlerInterceptor {
    public static final Logger LOGGER = LoggerFactory.getLogger("HandlerInterceptor");

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        String[] split = request.getRequestURI().split("/");
        if (split.length>0){
            switch (split[1]){
                case "about":
                case "articles":
                case "article":{
                    //查询匹配签名
                    String token= request.getHeader("Req");
                    final String sign= "1011011011011";
                    if(sign.equals(token)){
                        return true;
                    }
                    else{
                        LOGGER.warn(request.getRemoteAddr() + " " + request.getRequestURL());
                        LOGGER.warn("错误--" + "Req token转换错误"+" "+token);
                        return false;
                    }
                }
                default:{
                    return true;
                }
            }
        }
        else return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
    }
}
