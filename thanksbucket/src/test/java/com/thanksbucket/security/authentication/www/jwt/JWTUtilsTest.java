package com.thanksbucket.security.authentication.www.jwt;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.thanksbucket.domain.member.MemberRole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JWTUtilsTest {

    @Autowired
    private JWTUtils jwtUtils;

    @Test
    void generateToken() {
        String jwtToken = jwtUtils.generateToken(1L, "testemail@naver.com", "testnickname", List.of(ROLE_USER));
        assertThat(jwtToken).isNotNull();
    }

    @Test
    void decodeToken() {
        String jwtToken = jwtUtils.generateToken(1L, "testemail@naver.com", "testnickname", List.of(ROLE_USER));
        assertThat(jwtUtils.getMemberId(jwtToken)).isEqualTo(1L);
        assertThat(jwtUtils.getEmail(jwtToken)).isEqualTo("testemail@naver.com");
        assertThat(jwtUtils.getNickname(jwtToken)).isEqualTo("testnickname");
        assertThat(jwtUtils.getAuthorities(jwtToken)).contains(ROLE_USER);
    }
}
