package com.example.oauthpractice.dto.member;

import com.example.oauthpractice.entity.Member;
import lombok.*;

/**
 * @author : 김 기 영
 * @date : 2021. 06. 16.
 *
 * modifier :
 * modify-date :
 * description :
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberUpdateReqDto {

    private String username;
    private String nickname;
    private String email;

    public Member toEntity(){
        Member member = Member.builder()
                .nickname(nickname)
                .email(email)
                .build();

        return member;
    }
}
