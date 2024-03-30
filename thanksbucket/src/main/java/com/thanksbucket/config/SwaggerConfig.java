package com.thanksbucket.config;

import com.thanksbucket.security.authentication.CustomAuthenticationProcessingFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Value("${swagger.api.title}")
    private String title;
    @Value("${swagger.api.description}")
    private String description;
    @Value("${swagger.api.url}")
    private String url;

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title(title)
                .description(description)
                .version("v1");
        Server server = new Server().url(url);

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.COOKIE)
                .name("JSESSIONID");
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("JSESSIONID");

        return new OpenAPI()
                .servers(List.of(server))
                .components(new Components().addSecuritySchemes("JSESSIONID", securityScheme))
                .addSecurityItem(securityRequirement)
                .info(info);
    }


    @Bean
    //org.springdoc.security.SpringdocSecurityConfiguration
    public OpenApiCustomizer springSecurityLoginEndpointCustomiser(ApplicationContext applicationContext) {
        FilterChainProxy filterChainProxy = applicationContext.getBean(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME, FilterChainProxy.class);
        return openAPI -> {
            for (SecurityFilterChain filterChain : filterChainProxy.getFilterChains()) {
                Optional<CustomAuthenticationProcessingFilter> optionalFilter =
                        filterChain.getFilters().stream()
                                .filter(CustomAuthenticationProcessingFilter.class::isInstance)
                                .map(CustomAuthenticationProcessingFilter.class::cast)
                                .findAny();
                Optional<DefaultLoginPageGeneratingFilter> optionalDefaultLoginPageGeneratingFilter =
                        filterChain.getFilters().stream()
                                .filter(DefaultLoginPageGeneratingFilter.class::isInstance)
                                .map(DefaultLoginPageGeneratingFilter.class::cast)
                                .findAny();
                if (optionalFilter.isPresent()) {
                    CustomAuthenticationProcessingFilter customAuthenticationProcessingFilter = optionalFilter.get();
                    Operation operation = new Operation();
                    Schema<?> schema = new ObjectSchema()
                            .addProperty(customAuthenticationProcessingFilter.getUsernameParameter(), new StringSchema())
                            .addProperty(customAuthenticationProcessingFilter.getPasswordParameter(), new StringSchema());
                    String mediaType = org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//                    if (optionalDefaultLoginPageGeneratingFilter.isPresent()) {
//                        DefaultLoginPageGeneratingFilter defaultLoginPageGeneratingFilter = optionalDefaultLoginPageGeneratingFilter.get();
//                        Field formLoginEnabledField = FieldUtils.getDeclaredField(DefaultLoginPageGeneratingFilter.class, "formLoginEnabled", true);
//                        try {
//                            boolean formLoginEnabled = (boolean) formLoginEnabledField.get(defaultLoginPageGeneratingFilter);
//                            if (formLoginEnabled)
//                                mediaType = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
//                        }
//                        catch (IllegalAccessException e) {
//                            LOGGER.warn(e.getMessage());
//                        }
//                    }
                    RequestBody requestBody = new RequestBody().content(new Content().addMediaType(mediaType, new MediaType().schema(schema)));
                    operation.requestBody(requestBody);
                    ApiResponses apiResponses = new ApiResponses();
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.OK.value()), new ApiResponse().description(HttpStatus.OK.getReasonPhrase()));
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.FORBIDDEN.value()), new ApiResponse().description(HttpStatus.FORBIDDEN.getReasonPhrase()));
                    operation.responses(apiResponses);
                    operation.addTagsItem("auth");
                    PathItem pathItem = new PathItem().post(operation);
                    try {
                        Field requestMatcherField = AbstractAuthenticationProcessingFilter.class.getDeclaredField("requiresAuthenticationRequestMatcher");
                        requestMatcherField.setAccessible(true);
                        AntPathRequestMatcher requestMatcher = (AntPathRequestMatcher) requestMatcherField.get(customAuthenticationProcessingFilter);
                        String loginPath = requestMatcher.getPattern();
                        requestMatcherField.setAccessible(false);
                        openAPI.getPaths().addPathItem(loginPath, pathItem);
                    } catch (NoSuchFieldException | IllegalAccessException |
                             ClassCastException ignored) {
                        // Exception escaped
//                        LOGGER.trace(ignored.getMessage());
                    }
                }
            }
        };
    }
}
