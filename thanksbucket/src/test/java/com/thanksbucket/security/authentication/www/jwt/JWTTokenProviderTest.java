package com.thanksbucket.security.authentication.www.jwt;

import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.MemberRepository;
import com.thanksbucket.security.authentication.userdetails.AuthMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.thanksbucket.domain.member.MemberRole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JWTTokenProviderTest {
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JWTUtils jwtUtils;

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JWTTokenProvider(jwtUtils);
        memberRepository.save(new Member("testemail@naver.com", "testnickname"));
    }

    @Test
    void authenticate() {
        //given jwt 토큰 생성
        String token = jwtUtils.generateToken("testemail@naver.com", "testnickname", List.of(ROLE_USER));

        //when jwt 토큰 인증
        JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken) jwtTokenProvider.authenticate(
                JWTAuthenticationToken.unauthenticated("Bearer " + token)
        );

        //then jwt 토큰 인증 결과 확인
        assertThat(jwtAuthenticationToken.getPrincipal()).isInstanceOf(AuthMember.class);
        AuthMember authMember = (AuthMember) jwtAuthenticationToken.getPrincipal();
        assertThat(authMember.getEmail()).isEqualTo("testemail@naver.com");
        assertThat(authMember.getNickname()).isEqualTo("testnickname");
        assertThat(authMember.getAuthorities()).containsExactly(ROLE_USER);
    }
}
