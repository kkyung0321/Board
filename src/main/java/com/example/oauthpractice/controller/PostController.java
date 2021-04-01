package com.example.oauthpractice.controller;

import com.example.oauthpractice.config.AuthenticationFacade;
import com.example.oauthpractice.dto.post.*;
import com.example.oauthpractice.entity.Post;
import com.example.oauthpractice.service.MemberService;
import com.example.oauthpractice.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final AuthenticationFacade authenticationFacade;

    @GetMapping(value = {"/", "/home"})
    public String getHome(@PageableDefault(page = 0, size = 10,
            direction = Sort.Direction.DESC, sort = "id") Pageable pageable,
                          Model model) {

        Page<PostsResDto> postsResDtos = postService.postsWithPage(pageable);
        model.addAttribute("postsResDtos", postsResDtos);
        return "home";
    }

    @GetMapping("/writePost")
    public String getWrite() {
        return "write";
    }

    @PostMapping("/writePost")
    public String write(@ModelAttribute PostReqDto postReqDto) {

        Post post = postReqDto.toEntity();
        postService.write(post);

        return "redirect:/home";
    }

    @GetMapping("/post")
    public String readOne(@RequestParam("postId") Long postId, @PageableDefault(page = 0, size = 5,
            direction = Sort.Direction.DESC, sort = "id") Pageable pageable,
                          Model model) {

        PostAndRepliesResDto postAndRepliesResDto = postService.readOneWithReplies(postId, pageable);
        model.addAttribute("postAndRepliesResDto", postAndRepliesResDto);

        return "post";
    }

    @GetMapping("/editPost")
    public String getEdit(@RequestParam("postId") Long postId, Model model) {

        PostResDto postResDto = postService.readOne(postId);

        model.addAttribute("postResDto", postResDto);
        return "write";
    }

    @PostMapping("/editPost")
    public String edit(Long postId, String title, String content, RedirectAttributes redirectAttributes) {

        postService.edit(postId, title, content);

        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/post";
    }

    @GetMapping("/deletePost")
    public String delete(Long postId) {
        postService.delete(postId);

        return "redirect:/home";
    }

    @GetMapping("/search")
    public String searchPosts(String keyword, @PageableDefault(size = 10, page = 0,
            direction = Sort.Direction.DESC, sort = "id") Pageable pageable,
                              Model model) {

        Page<PostsResDto> postsResDtos = postService.searchPosts(keyword, pageable);

        model.addAttribute("keyword", keyword);
        model.addAttribute("postsResDtos", postsResDtos);
        return "search";
    }

    @GetMapping("/myposts")
    public String myPosts(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC,
            sort = "id") Pageable pageable, Model model) {

        Page<MyPostsResDto> myPostsResDtos = postService.myPosts(pageable);

        model.addAttribute("myPostsResDtos", myPostsResDtos);
        return "myPosts";
    }
}
