package com.thanksbucket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${cors.allowed-origins}")
    private List<String> CORS_ALLOWED_ORIGIN;
    @Value("${cors.allowed-methods}")
    private List<String> CORS_ALLOWED_METHODS;
    @Value("${cors.allowed-headers}")
    private List<String> CORS_ALLOWED_HEADERS;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(CORS_ALLOWED_ORIGIN.toArray(new String[0]))
                .allowedMethods(CORS_ALLOWED_METHODS.toArray(new String[0]))
                .allowedHeaders(CORS_ALLOWED_HEADERS.toArray(new String[0]))
                .allowCredentials(true);
    }
}
