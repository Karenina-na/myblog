package com.augenstern.config;

import com.augenstern.controller.interceptor.AllProjectInterceptor;
import com.augenstern.controller.interceptor.RootInterceptor;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;

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
     *
     * 解决本地静态资源问题
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //图片资源
        registry.addResourceHandler("/image/**")
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX+"./"+
                        "image" + System.getProperty("file.separator"));

        //开发文档
        registry.addResourceHandler("/doc.html").addResourceLocations("/doc.html");

        //前端资源
        registry.addResourceHandler("/**").addResourceLocations(
                ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 拦截器链
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(allProjectInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/image/**");
        registry.addInterceptor(rootInterceptor)
                .addPathPatterns("/rootManager/**").excludePathPatterns("/image/**");
    }
}
