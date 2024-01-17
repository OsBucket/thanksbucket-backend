package com.thanksbucket.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanksbucket.security.service.MemberContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
@Slf4j
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MemberContext memberContext = (MemberContext) authentication.getPrincipal();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        HttpSession session = request.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        //TODO Secure 추가
        String header = "JSESSIONID=" + session.getId() + "; SameSite=Strict; path=/;";
        response.setHeader("Set-Cookie", header);


        log.info("로그인 성공: user:{}, JSESSIONID:{}", memberContext.getMember().getMemberId(), session.getId());
        objectMapper.writeValue(response.getWriter(), new HashMap<>() {{
            put("JSESSIONID", session.getId());
        }});
    }
}
