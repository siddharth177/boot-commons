package io.github.siddharth177.bootcommons.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Configuration
@Slf4j
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${bootcommons.swagger.title}") String title,
            @Value("${bootcommons.swagger.description}") String description,
            @Value("${bootcommons.swagger.version}") String version,
            @Value("${bootcommons.swagger.contact.name}") String contactName,
            @Value("${bootcommons.swagger.contact.email}") String contactEmail) {

        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(new Info());
        openAPI.getInfo().setTitle(title);
        openAPI.getInfo().setDescription(description);
        openAPI.getInfo().setVersion(version);
        openAPI.getInfo().setContact(new io.swagger.v3.oas.models.info.Contact());
        openAPI.getInfo().getContact().setEmail(contactEmail);
        openAPI.getInfo().getContact().setName(contactName);
        return openAPI;
    }

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
