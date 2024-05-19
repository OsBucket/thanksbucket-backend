package com.thanksbucket.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);
}
