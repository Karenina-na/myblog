package com.augenstern.config;

import com.augenstern.controller.interceptor.AllProjectInterceptor;
import com.augenstern.controller.interceptor.RootInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * SpringMVC配置类
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Resource
    private AllProjectInterceptor allProjectInterceptor;

    @Resource
    private RootInterceptor rootInterceptor;

    /**
     * 静态资源路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/*");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/*");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/*");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/*");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 拦截器链
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(allProjectInterceptor).addPathPatterns("/**");
        registry.addInterceptor(rootInterceptor).addPathPatterns("/rootManager/**");
    }
}
