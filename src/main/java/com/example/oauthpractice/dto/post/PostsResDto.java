package com.example.oauthpractice.dto.post;

import com.example.oauthpractice.entity.Post;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostsResDto {

    private Long postId;
    private String title;
    private String content;
    private String nickname;
    private LocalDate createdDate;

    public static Page<PostsResDto> of(Page<Post> posts) {
        Page<PostsResDto> postsResDtos = posts.map(post -> PostsResDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .nickname(post.getPMember().getNickname())
                .createdDate(post.getCreatedDate())
                .build());

        return postsResDtos;
    }
}
