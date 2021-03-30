package com.example.oauthpractice.dto;

import com.example.oauthpractice.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class MyRepliesResDto {

    private Long postId;
    private Long replyId;
    private String rContent;
    private LocalDate createdDate;

    public static Page<MyRepliesResDto> of(Page<Reply> replies) {
        Page<MyRepliesResDto> myRepliesResDtos = replies.map(reply -> MyRepliesResDto.builder()
                .postId(reply.getPost().getId())
                .replyId(reply.getId())
                .rContent(reply.getRContent())
                .createdDate(reply.getCreatedDate())
                .build());

        return myRepliesResDtos;
    }
}
