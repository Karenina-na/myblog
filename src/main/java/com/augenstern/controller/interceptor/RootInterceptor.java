package com.augenstern.controller.interceptor;

import com.augenstern.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户拦截器
 */
@Component
public class RootInterceptor implements HandlerInterceptor {
    public static final Logger LOGGER = LoggerFactory.getLogger("HandlerInterceptor");

   @Resource
    private MyService myService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //管理签名匹配
        String token= request.getHeader("Man");
        final String sign= "10101101111000";
        if(sign.equals(token)){
            return true;
        }
        else{
            LOGGER.warn(request.getRemoteAddr() + " " + request.getRequestURL());
            LOGGER.warn("错误--" + "Man token转换错误"+" "+token);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
