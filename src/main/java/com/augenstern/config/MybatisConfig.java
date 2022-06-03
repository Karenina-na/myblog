package com.augenstern.config;

import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Mybatis工厂对象
 */
@Configuration
public class MybatisConfig {

    /**
     * 数据库工厂
     * @param dataSource
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.augenstern.domain");

        PageInterceptor pageInterceptor = new PageInterceptor();        //分页插件
        Properties properties = new Properties();
        pageInterceptor.setProperties(properties);

        factory.setPlugins(pageInterceptor);
        return factory;
    }

    /**
     * 包扫描
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.augenstern.dao");
        return mapperScannerConfigurer;
    }
}
