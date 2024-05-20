package com.thanksbucket.security.oauth2;

import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.SocialType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {
    private final Long userId;
    private final SocialType socialType;
    private final String socialId;
    private final String email;
    private final String nickname;
    private final String imageUrl;

    @Builder
    private CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey, Long userId, SocialType socialType, String socialId, String email, String nickname, String imageUrl) {
        super(authorities, attributes, nameAttributeKey);
        this.userId = userId;
        this.socialType = socialType;
        this.socialId = socialId;
        this.email = email;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }

    public static CustomOAuth2User ofMember(Member member, Map<String, Object> attributes, String nameAttributeKey) {
        return CustomOAuth2User.builder()
                .attributes(attributes)
                .nameAttributeKey(nameAttributeKey)

                .userId(member.getId())
                .socialType(member.getSocialType())
                .socialId(member.getSocialId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .imageUrl(member.getImageUrl())
                .authorities(List.of(member.getMemberRole().toSimpleGrantedAuthority()))
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
