package com.thanksbucket.core.member;

import com.thanksbucket.core.member.query.domain.Member;
import com.thanksbucket.core.member.query.domain.SocialType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String email);

  Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
