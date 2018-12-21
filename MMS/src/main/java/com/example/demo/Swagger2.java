package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by next on 2018/10/28.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 自定义该API工具展示信息，标题、描述、更新的地址、作者信息、版本等
     *
     * ApiInfoBuilder title(String str): 设置标题
     * ApiInfoBuilder description(String str): 描述
     * ApiInfoBuilder termsOfServiceUrl(String str): 更新地址
     * ApiInfoBuilder contact(String str): 作者信息
     * ApiInfoBuilder version(String str): 版本
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("医院取药、检药、开检查单  APIs")
                .description("与药物有关的接口")
                .version("1.0.0")
                .build();
    }
}

