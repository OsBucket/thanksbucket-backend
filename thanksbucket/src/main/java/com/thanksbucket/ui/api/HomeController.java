package com.thanksbucket.ui.api;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final SessionRegistry sessionRegistry;

    @GetMapping("/")
    public String getAllSessionRegistry(HttpSession session) {
        sessionRegistry.getAllPrincipals().forEach(System.out::println);
        return "home";
    }

    @GetMapping("/api/users")
    public String users(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return authentication.getName();
    }
}
