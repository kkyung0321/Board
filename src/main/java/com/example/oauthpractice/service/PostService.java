package com.example.oauthpractice.service;

import com.example.oauthpractice.config.AuthenticationFacade;
import com.example.oauthpractice.dto.post.MyPostsResDto;
import com.example.oauthpractice.dto.post.PostAndRepliesResDto;
import com.example.oauthpractice.dto.post.PostResDto;
import com.example.oauthpractice.dto.post.PostsResDto;
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
public class PostService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final AuthenticationFacade authenticationFacade;

    public void write(Post post) {
        Member member = (Member) authenticationFacade.getAuthentication().getPrincipal();
        Member member1 = memberRepository.findByUsername(member.getUsername()).get();

        post.addPost(member1);
        postRepository.save(post);
    }

    public PostResDto readOne(Long postId) {
        Post post = postRepository.findById(postId).get();

        PostResDto postResDto = PostResDto.of(post);

        return postResDto;
    }

    public PostAndRepliesResDto readOneWithReplies(Long postId, Pageable pageable) {

        Post post = postRepository.findPostWithMember(postId).get();

        Page<Reply> repliesWithMember = replyRepository.findRepliesWithMember(postId, pageable);

        PostAndRepliesResDto postAndRepliesResDto = PostAndRepliesResDto.of(post, repliesWithMember);

        return postAndRepliesResDto;
    }

    public void edit(Long postId, String title, String content) throws IllegalArgumentException {
        Post post = postRepository.findById(postId).get();

        post.edit(title, content);
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    public Page<PostsResDto> postsWithPage(Pageable pageable) {
        Page<Post> postsWithMember = postRepository.findPostsWithMember(pageable);

        Page<PostsResDto> postsResDtos = PostsResDto.of(postsWithMember);

        return postsResDtos;
    }

    public Page<PostsResDto> searchPosts(String keyword, Pageable pageable) {

        Page<Post> allByTitleContainingOrContentContaining =
                postRepository.findAllByTitleContainingOrContentContaining(keyword, keyword, pageable);

        Page<PostsResDto> postsResDtos = PostsResDto.of(allByTitleContainingOrContentContaining);

        return postsResDtos;
    }

    public Page<MyPostsResDto> myPosts(Pageable pageable) {

        Member member = (Member) authenticationFacade.getAuthentication().getPrincipal();
        Member member1 = memberRepository.findByUsername(member.getUsername()).get();
        Page<Post> myPosts = postRepository.findMyPosts(member1.getId(), pageable);
        Page<MyPostsResDto> myPostsResDtos = MyPostsResDto.of(myPosts);

        return myPostsResDtos;
    }
}
