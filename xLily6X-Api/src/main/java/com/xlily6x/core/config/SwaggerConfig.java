package com.xlily6x.core.config;

import io.swagger.annotations.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by xiaowenlong on 11/12/2017.
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"com.xlily6x.api"})
public class SwaggerConfig {

    @Bean
    public Docket platformApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("full-platform").apiInfo(apiInfo())
                .forCodeGeneration(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("xLily6X-API").description("©2017 Copyright. Powered By xLily6X.")
                // .termsOfServiceUrl("")
                .contact(new springfox.documentation.service.Contact("xLily6X", "", "xiaowenlong2015@icloud.com")).license("Apache License Version 2.0")
                .licenseUrl("http://www.xlily6X.com").version("2.0").build();
    }
}
