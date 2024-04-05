package com.thanksbucket.security.authentication.www.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO 검증은 filter말고 provider에서
        try {
            log.debug("JWT Filter PASS START");
            String header = request.getHeader(AUTHORIZATION_HEADER);
            if (header == null) {
                throw new AuthenticationServiceException("인증에 실패하였습니다.");
            }
            String[] split = header.split(" ");
            if (split.length < 2 && !split[0].equals(TOKEN_PREFIX)) {
                throw new AuthenticationServiceException("유효하지 않은 토큰 형식입니다.");
            }
            String token = split[1];
            Authentication unauthenticated = JWTAuthenticationToken.unauthenticated(token);
            Authentication authResult = this.authenticationManager.authenticate(unauthenticated);

            SecurityContextHolder.getContext().setAuthentication(authResult);
        } catch (InternalAuthenticationServiceException failed) {
            this.logger.error("An internal error occurred while trying to authenticate the user.", failed);
        } catch (AuthenticationException ex) {
            this.logger.error(ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }

}
