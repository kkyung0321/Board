package com.example.oauthpractice.dto.post;

import com.example.oauthpractice.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostReqDto {

    private String title;
    private String content;

    public Post toEntity() {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();

        return post;
    }
}
