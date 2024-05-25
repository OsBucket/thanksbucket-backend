package com.thanksbucket.security.config;

import com.thanksbucket.security.authentication.www.CustomUnauthorizedEntryPoint;
import com.thanksbucket.security.authentication.www.jwt.JWTAuthenticationFilter;
import com.thanksbucket.security.authentication.www.jwt.JWTTokenProvider;
import com.thanksbucket.security.authentication.www.jwt.JWTUtils;
import com.thanksbucket.security.oauth2.handler.OAuth2LoginFailureHandler;
import com.thanksbucket.security.oauth2.handler.OAuth2LoginSuccessHandler;
import com.thanksbucket.security.oauth2.service.CustomOAuth2UserService;
import com.thanksbucket.security.oauth2.service.HttpCookieOAuth2AuthorizationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTUtils jwtUtils;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Value("${cors.allowed-origins}")
    private List<String> CORS_ALLOWED_ORIGIN;
    @Value("${cors.allowed-methods}")
    private List<String> CORS_ALLOWED_METHODS;
    @Value("${cors.allowed-headers}")
    private List<String> CORS_ALLOWED_HEADERS;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/api/health", "/api").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/index.html").permitAll() //TODO 임시 FE AuthorizationCode 대체
                        .requestMatchers("/api/auth/signup", "/api/occupations", "/api/auth/profile").hasAnyRole("GUEST", "USER")
                        .requestMatchers("/api/**").hasRole("USER")
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new CustomUnauthorizedEntryPoint()))
                .oauth2Login(oauth2Login -> oauth2Login
                        .successHandler(oAuth2LoginSuccessHandler)
                        .failureHandler(new OAuth2LoginFailureHandler())
                        .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                                .authorizationRequestRepository(new HttpCookieOAuth2AuthorizationRequestRepository())
                        )
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(customOAuth2UserService)
                        )
                )
//                .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        http.cors((httpSecurityCorsConfigurer -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setAllowedOrigins(CORS_ALLOWED_ORIGIN);
            corsConfiguration.setAllowedMethods(CORS_ALLOWED_METHODS);
            corsConfiguration.setAllowedHeaders(CORS_ALLOWED_HEADERS);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);
            httpSecurityCorsConfigurer.configurationSource(source);
        }));

        return http.build();
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public JWTTokenProvider jwtTokenProvider(JWTUtils jwtUtils) {
        return new JWTTokenProvider(jwtUtils);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(jwtTokenProvider(jwtUtils));
        return authenticationManagerBuilder.build();
    }

    private JWTAuthenticationFilter jwtFilter() throws Exception {
        return new JWTAuthenticationFilter(authenticationManager(null));
    }
}
