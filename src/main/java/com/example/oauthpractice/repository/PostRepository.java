package com.example.oauthpractice.repository;

import com.example.oauthpractice.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p join fetch p.pMember m where p.id=:postId")
    Optional<Post> findPostWithMember(@Param("postId") Long postId);

    @Query(value = "select p from Post p join fetch p.pMember m",
            countQuery = "select count(p) from Post p")
    Page<Post> findPostsWithMember(Pageable pageable);

    Page<Post> findAllByTitleContainingOrContentContaining(@Param("title") String title,
                                                           @Param("content") String content,
                                                           Pageable pageable);

    @Query(value = "select p from Post p where p.pMember.id = :id")
    Page<Post> findMyPosts(@Param("id") Long id, Pageable pageable);
}
