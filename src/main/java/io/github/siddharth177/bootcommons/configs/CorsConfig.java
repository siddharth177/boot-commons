package io.github.siddharth177.bootcommons.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Cross-Origin Resource Sharing (CORS).
 */
@Configuration
public class CorsConfig {

    @Value("${bootcommons.cors.allowed-origins:*}")
    private String[] allowedOrigins;

    @Value("${bootcommons.cors.allowed-methods:GET,POST,PUT,DELETE,OPTIONS}")
    private String[] allowedMethods;

    @Value("${bootcommons.cors.allowed-headers:*}")
    private String[] allowedHeaders;

    @Value("${bootcommons.cors.allow-credentials:true}")
    private boolean allowCredentials;

    /**
     * Default constructor for {@code CorsConfig}.
     */
    public CorsConfig() {
        // Default constructor
    }

    /**
     * Creates a {@link WebMvcConfigurer} bean to configure CORS.
     *
     * @return The {@link WebMvcConfigurer} bean.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods(allowedMethods)
                        .allowedHeaders(allowedHeaders)
                        .allowCredentials(allowCredentials);
            }
        };
    }
}
