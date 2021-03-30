package com.example.oauthpractice.dto.post;

import com.example.oauthpractice.entity.Post;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostResDto {

    private Long postId;
    private String title;
    private String content;

    public static PostResDto of(Post post) {
        PostResDto postResDto = PostResDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        return postResDto;
    }
}
