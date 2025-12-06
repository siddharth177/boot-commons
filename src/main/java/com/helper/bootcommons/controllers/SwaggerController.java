package com.helper.bootcommons.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(
        info = @Info(
                title = "Hazelcast Sample Application",
                description = "Sample Application to demonstrate use of hazelcast.",
                version = "${info.app.version}",
                contact = @Contact(
                        name = "Siddharth Katiyar",
                        email = "siddharth.dev177@gmail.com"
                )
        )

)
@Tag(name = "swagger setup controller", description = "controller created to set up swagger for the application. open swagger on: http://localhost:<port>/hazelcastSample/swagger-ui/index.html#/")
public class SwaggerController {


    @Bean
    public GroupedOpenApi publicApiGroup() {
        return GroupedOpenApi.builder()
                .group("hazelcast")
                .pathsToMatch("/**")
                .build();
    }
}


