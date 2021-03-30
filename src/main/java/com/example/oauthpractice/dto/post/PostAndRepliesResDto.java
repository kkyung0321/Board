package com.example.oauthpractice.dto.post;

import com.example.oauthpractice.dto.reply.ReplyResDto;
import com.example.oauthpractice.entity.Post;
import com.example.oauthpractice.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostAndRepliesResDto {

    private Long postId;
    private String title;
    private String content;
    private String username;
    private String nickname;
    private Page<ReplyResDto> replies;
    private LocalDate createdDate;

    public static PostAndRepliesResDto of(Post post, Page<Reply> replies) {

        PostAndRepliesResDto postAndRepliesResDto = PostAndRepliesResDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .nickname(post.getPMember().getNickname())
                .username(post.getPMember().getUsername())
                .replies(replies.map(reply -> ReplyResDto.of(reply)))
                .createdDate(post.getCreatedDate())
                .build();

        return postAndRepliesResDto;
    }
}
