package com.example.oauthpractice.service;

import com.example.oauthpractice.config.AuthenticationFacade;
import com.example.oauthpractice.dto.MyRepliesResDto;
import com.example.oauthpractice.entity.Member;
import com.example.oauthpractice.entity.Post;
import com.example.oauthpractice.entity.Reply;
import com.example.oauthpractice.repository.MemberRepository;
import com.example.oauthpractice.repository.PostRepository;
import com.example.oauthpractice.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final AuthenticationFacade authenticationFacade;

    public void write(Long postId, Reply reply) {
        Member member = (Member) authenticationFacade.getAuthentication().getPrincipal();
        log.info(String.valueOf(member.getId()));
        Member member1 = memberRepository.findByUsername(member.getUsername()).get();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("id doesn't exist : " + postId));

        reply.addMember(member1);
        reply.addPost(post);

        replyRepository.save(reply);
    }

    public void edit(Long replyId, String rContent) throws IllegalArgumentException {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("id doesn't exist : " + replyId));

        reply.edit(rContent);
    }

    public void delete(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    public Page<MyRepliesResDto> myReplies(Pageable pageable) {
        Member member = (Member) authenticationFacade.getAuthentication().getPrincipal();
        Member member1 = memberRepository.findByUsername(member.getUsername()).get();

        Page<Reply> myReplies = replyRepository.findMyReplies(member1.getId(), pageable);
        Page<MyRepliesResDto> myRepliesResDtos = MyRepliesResDto.of(myReplies);

        return myRepliesResDtos;
    }
}
