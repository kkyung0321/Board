package com.example.oauthpractice.dto.post;

import com.example.oauthpractice.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class MyPostsResDto {

    private Long postId;
    private String title;
    private String content;
    private LocalDate createdDate;

    public static Page<MyPostsResDto> of(Page<Post> posts) {
        Page<MyPostsResDto> myPostsResDtos = posts.map(post -> MyPostsResDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .build());

        return myPostsResDtos;
    }
}
