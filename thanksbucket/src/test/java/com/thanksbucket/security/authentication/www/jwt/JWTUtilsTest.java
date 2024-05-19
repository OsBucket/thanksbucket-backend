package com.thanksbucket.security.authentication.www.jwt;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JWTUtilsTest {

    @Autowired
    private JWTUtils jwtUtils;

    @Test
    void generateToken() {
        String jwtToken = jwtUtils.generateToken("testemail@naver.com", "testnickname", List.of(new SimpleGrantedAuthority("ROLE_USER")));
        assertThat(jwtToken).isNotNull();
    }

    @Test
    void decodeToken() {
        String jwtToken = jwtUtils.generateToken("testemail@naver.com", "testnickname", List.of(new SimpleGrantedAuthority("ROLE_USER")));
        assertThat(jwtUtils.getEmail(jwtToken)).isEqualTo("testemail@naver.com");
        assertThat(jwtUtils.getNickname(jwtToken)).isEqualTo("testnickname");
    }
}
