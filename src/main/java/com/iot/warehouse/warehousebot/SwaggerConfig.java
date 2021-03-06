package com.iot.warehouse.warehousebot;

import static springfox.documentation.builders.PathSelectors.any;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.iot.warehouse.warehousebot"))
          .paths(any())
          .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
            "Warehouse BOT REST API",
            "BOT management API.",
            "API 1.0",
            "Terms of service",
            new Contact("SAP Ariba IOT Team", "www.sap.com", "deepak.singhvi@sap.com"),
            "License of API", "API license URL", Collections.emptyList());
    }
}