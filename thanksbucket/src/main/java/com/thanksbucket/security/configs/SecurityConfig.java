package com.thanksbucket.security.configs;

import com.thanksbucket.security.authentication.LoginAuthenticationFilter;
import com.thanksbucket.security.authentication.LoginAuthenticationProvider;
import com.thanksbucket.security.authentication.www.CustomUnauthorizedEntryPoint;
import com.thanksbucket.security.authentication.www.session.SessionAuthenticationFailureHandler;
import com.thanksbucket.security.authentication.www.session.SessionAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
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
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true))
                .authenticationProvider(new LoginAuthenticationProvider(passwordEncoder(), userDetailsService))
                .addFilterBefore(loginFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //내서버가 응답을 할때 json을 자바스크립트에서 처리할 수 있게 할지
        config.addAllowedOriginPattern("*"); //모든 아이피를 응답허용
        config.addAllowedHeader("*"); //모든 header 응답허용
        config.addAllowedMethod("*"); //모든 post,get,put 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter loginFilter(AuthenticationManager authenticationManager) {
        UsernamePasswordAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(authenticationManager);
        loginAuthenticationFilter.setAuthenticationSuccessHandler(new SessionAuthenticationSuccessHandler());
        loginAuthenticationFilter.setAuthenticationFailureHandler(new SessionAuthenticationFailureHandler());
        return loginAuthenticationFilter;
    }
}
