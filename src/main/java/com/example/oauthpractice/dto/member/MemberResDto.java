package com.example.oauthpractice.dto.member;

import com.example.oauthpractice.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResDto {

    private String username;
    private String nickname;
    private String email;

    public static MemberResDto of(Member member) {
        MemberResDto memberResDto = MemberResDto.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .build();

        return memberResDto;
    }
}
