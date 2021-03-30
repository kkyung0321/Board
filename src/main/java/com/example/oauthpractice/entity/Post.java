package com.example.oauthpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member pMember;

    @Builder.Default
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    @CreatedDate
    private LocalDate createdDate;

    public void addPost(Member member) {
        this.pMember = member;
        member.getPosts().add(this);
    }

    public void edit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
