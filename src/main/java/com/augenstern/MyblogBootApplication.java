package com.augenstern;


import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCreateCacheAnnotation    //开启使用注解创建缓存 jetCache
@EnableMethodCache(basePackages = "com.augenstern.service")	//开启使用注解创建方法缓存 jetCache
public class MyblogBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogBootApplication.class, args);
    }

}
