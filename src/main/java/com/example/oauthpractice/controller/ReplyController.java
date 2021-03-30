package com.example.oauthpractice.controller;

import com.example.oauthpractice.dto.MyRepliesResDto;
import com.example.oauthpractice.dto.reply.ReplyReqDto;
import com.example.oauthpractice.entity.Reply;
import com.example.oauthpractice.service.ReplyService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/writeReply")
    public String write(Long postId, @ModelAttribute ReplyReqDto replyReqDto, RedirectAttributes redirectAttributes) {

        Reply reply = replyReqDto.toEntity();
        replyService.write(postId, reply);

        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/post";
    }

    @GetMapping("/deleteReply")
    public String delete(Long postId, Long replyId, RedirectAttributes redirectAttributes) {
        replyService.delete(replyId);

        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/post";
    }

    @GetMapping("/myreplies")
    public String myReplies(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC,
            sort = "id") Pageable pageable, Model model) {

        Page<MyRepliesResDto> myRepliesResDtos = replyService.myReplies(pageable);

        model.addAttribute("myRepliesResDtos", myRepliesResDtos);
        return "myReplies";
    }
}
