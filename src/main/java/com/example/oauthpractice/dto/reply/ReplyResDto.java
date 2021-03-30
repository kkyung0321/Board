package com.example.oauthpractice.dto.reply;

import com.example.oauthpractice.entity.Reply;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyResDto {

    private Long replyId;
    private String rContent;
    private String nickname;
    private LocalDate createdDate;

    public static ReplyResDto of(Reply reply) {
        ReplyResDto replyResDto = ReplyResDto.builder()
                .replyId(reply.getId())
                .rContent(reply.getRContent())
                .nickname(reply.getRMember().getNickname())
                .createdDate(reply.getCreatedDate())
                .build();

        return replyResDto;
    }
}
