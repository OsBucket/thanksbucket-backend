package com.thanksbucket.security.authentication;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

    public LoginAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        super(passwordEncoder);
        super.setUserDetailsService(userDetailsService);
        super.setHideUserNotFoundExceptions(false);
    }
}
