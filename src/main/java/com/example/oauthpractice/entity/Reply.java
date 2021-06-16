package com.example.oauthpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Reply {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String rContent;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member rMember;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @CreatedDate
    private LocalDate createdDate;

    public void addMember(Member member) {
        this.rMember = member;
        member.getReplies().add(this);
    }

    public void addPost(Post post) {
        this.post = post;
        post.getReplies().add(this);
    }

    public void editReply(String rContent) {
        this.rContent = rContent;
    }
}
