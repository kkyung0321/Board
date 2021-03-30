package com.example.oauthpractice.repository;

import com.example.oauthpractice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(@Param("username") String username);

    Long countByUsernameContaining(@Param("username") String username);

    Long countByNicknameContaining(@Param("nickname") String nickname);
}
