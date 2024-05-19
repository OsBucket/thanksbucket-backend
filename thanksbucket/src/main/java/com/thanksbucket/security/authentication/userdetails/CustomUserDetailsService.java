package com.thanksbucket.security.authentication.userdetails;

import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                            log.info("{}는 존재하지 않는 유저 입니다.", email);
                            return new UsernameNotFoundException("존재하지 않는 유저 입니다.");
                        }
                );
        return AuthMember.fromMember(member);
    }
}
