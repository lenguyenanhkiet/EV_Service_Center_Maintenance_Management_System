package com.swp391.ev_service_center.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info().title("API-service document").version("v1.0.0")
                .description("API service document")
                .license(new License().name("API License").url("http:domain.vn/license")))
                .servers(List.of(new Server().url("https://localhost:8080/").description("Server Test")));
    }
}
