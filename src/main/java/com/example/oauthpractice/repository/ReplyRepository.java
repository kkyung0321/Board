package com.example.oauthpractice.repository;

import com.example.oauthpractice.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query(value = "select r from Reply r join fetch r.rMember m where r.post.id = :postId",
            countQuery = "select count(r) from Reply r where r.post.id = :postId")
    Page<Reply> findRepliesByRMember(@Param("postId") Long postId, Pageable pageable);

    @Query(value = "select r from Reply r join fetch r.post where r.rMember.id = :memberId",
            countQuery = "select count(r) from Reply r where r.rMember.id = :memberId")
    Page<Reply> findMyReplies(@Param("memberId") Long memberId, Pageable pageable);
}
