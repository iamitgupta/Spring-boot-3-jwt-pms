package com.pms.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Management System API")
                        .version("1.0.0")
                        .description("API documentation for the Product Management System.")
                        .termsOfService("http://example.com/terms")
                        .contact(new Contact()
                                .name("Amit Gupta")
                                .email("amitl@pms.com")
                                .url("http://example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Boot Documentation")
                        .url("https://spring.io/projects/spring-boot"));
    }
}
