package com.thanksbucket.security.provider;

import com.thanksbucket.security.service.MemberContext;
import com.thanksbucket.security.token.AjaxAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, memberContext.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberContext, null, memberContext.getAuthorities());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AjaxAuthenticationToken.class);
    }
}
