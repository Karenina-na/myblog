package com.augenstern.config;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.ArrayList;

/**
 * swagger配置文件
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class SwaggerConfiguration {
    /**
     * Swagger bean
     * @param environment
     * @return
     */
    @Bean
    public Docket docket(Environment environment) {
        //判断环境
        Profiles p = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(p);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .groupName("Root")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.augenstern.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API信息说明
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Myblog",
                "Myblog API文档",
                "1.0",//版本信息
                "https://www.wzxaugenstern.online",
                new Contact("Augenstern", "https://www.wzxaugenstern.online", "1539989223@qq.com"),
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
