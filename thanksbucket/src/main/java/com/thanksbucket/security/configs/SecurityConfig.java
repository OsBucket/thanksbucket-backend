package com.thanksbucket.security.configs;

import com.thanksbucket.security.authentication.CustomAuthenticationProcessingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //TODO 권한 조정 필요
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/api/health").permitAll()
                        .requestMatchers("/api/auth/login", "/api/auth/signup", "/api/occupations", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/**").hasRole("USER")
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling((exceptionHandling) -> {
                    exceptionHandling.authenticationEntryPoint(authenticationEntryPoint);
                })
                .sessionManagement((sessionManagement) -> {
                    sessionManagement.maximumSessions(1)
                            .maxSessionsPreventsLogin(true);
                })
                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomAuthenticationProcessingFilter ajaxLoginProcessingFilter() throws Exception {
        CustomAuthenticationProcessingFilter customAuthenticationProcessingFilter = new CustomAuthenticationProcessingFilter();
        customAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        customAuthenticationProcessingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        customAuthenticationProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return customAuthenticationProcessingFilter;
    }
}
