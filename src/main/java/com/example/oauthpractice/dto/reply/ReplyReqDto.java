package com.example.oauthpractice.dto.reply;

import com.example.oauthpractice.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReplyReqDto {

    private String rContent;

    public Reply toEntity() {
        Reply reply = Reply.builder()
                .rContent(rContent)
                .build();

        return reply;
    }
}
