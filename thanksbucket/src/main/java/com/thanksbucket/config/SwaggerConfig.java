package com.thanksbucket.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("ThanksBucket API")
                .description("ThanksBucket API")
                .version("v1");

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.COOKIE)
                .name("JSESSIONID");
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("JSESSIONID");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("JSESSIONID", securityScheme))
                .addSecurityItem(securityRequirement)
                .info(info);
    }
}
