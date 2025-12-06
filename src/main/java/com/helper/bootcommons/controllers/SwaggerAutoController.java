package com.helper.bootcommons.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "${bootcommons.swagger.title}",
                description = "${bootcommons.swagger.description}",
                version = "${bootcommons.swagger.version}",
                contact = @Contact(
                        name = "${bootcommons.swagger.contact.name}",
                        email = "${bootcommons.swagger.contact.email}"
                )
        )
)
public class SwaggerAutoController {

    @Bean
    public List<GroupedOpenApi> apiGroups(@Value("${bootcommons.swagger.groups}") List<String> groups) {
        return groups.stream()
                .map(group -> GroupedOpenApi.builder()
                        .group(group)
                        .pathsToMatch("/**")
                        .build())
                .toList();
    }
}
