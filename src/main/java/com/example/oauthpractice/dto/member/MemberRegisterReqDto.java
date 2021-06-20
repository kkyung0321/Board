package com.example.oauthpractice.dto.member;

import com.example.oauthpractice.entity.Member;
import com.example.oauthpractice.entity.Role;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberRegisterReqDto {

    private String username;
    private String password;
    private String nickname;
    private String email;

    public Member toEntity() {
        Member member = Member.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .nickname(nickname)
                .email(email)
                .role(Role.USER)
                .build();

        return member;
    }
}
