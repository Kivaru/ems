package com.godfreykivaru.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                            .title("Employment Management System API")
                            .description("API documentantation for managing employees")
                            .version("1.0")
                            .contact(new Contact()
                            .name("Godfrey Kivaru")
                            .email("kivarugodfrey@gmail.com")));   
    }

}
