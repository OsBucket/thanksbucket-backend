package com.thanksbucket.security.configs;

import com.thanksbucket.security.authentication.LoginAuthenticationFilter;
import com.thanksbucket.security.authentication.www.CustomUnauthorizedEntryPoint;
import com.thanksbucket.security.authentication.www.session.SessionAuthenticationFailureHandler;
import com.thanksbucket.security.authentication.www.session.SessionAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/api/health").permitAll()
                        .requestMatchers("/api/auth/login", "/api/auth/signup", "/api/occupations", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/**").hasRole("USER")
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new CustomUnauthorizedEntryPoint()))
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                        .maximumSessions(1)
//                        .maxSessionsPreventsLogin(true))
                .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class)
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

    public UsernamePasswordAuthenticationFilter loginFilter() throws Exception {
        UsernamePasswordAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(authenticationManager(authenticationConfiguration));
        loginAuthenticationFilter.setAuthenticationSuccessHandler(new SessionAuthenticationSuccessHandler());
        loginAuthenticationFilter.setAuthenticationFailureHandler(new SessionAuthenticationFailureHandler());
        return loginAuthenticationFilter;
    }
}
