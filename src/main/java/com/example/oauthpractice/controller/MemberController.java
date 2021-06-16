package com.example.oauthpractice.controller;

import com.example.oauthpractice.config.AuthenticationFacade;
import com.example.oauthpractice.dto.member.MemberRegisterReqDto;
import com.example.oauthpractice.dto.member.MemberResDto;
import com.example.oauthpractice.dto.member.MemberUpdateReqDto;
import com.example.oauthpractice.entity.Member;
import com.example.oauthpractice.exception.SameNicknameException;
import com.example.oauthpractice.exception.SameUsernameException;
import com.example.oauthpractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationFacade authenticationFacade;

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute MemberRegisterReqDto memberRegisterReqDto, Model model) {

        log.info(memberRegisterReqDto.toString());

        try {
            memberService.usernameDuplicationCheck(memberRegisterReqDto.getUsername());
            memberService.nicknameDuplicationCheck(memberRegisterReqDto.getNickname());
            Member member = memberRegisterReqDto.toEntity();
            memberService.save(member);
        } catch (SameUsernameException e) {
            log.info(e.getMessage(), e);
            model.addAttribute("msg", e.getMessage());
            return "register";
        } catch (SameNicknameException e) {
            log.info(e.getMessage(), e);
            model.addAttribute("msg", e.getMessage());
            return "register";
        }

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/memberInfo")
    public String getMemberInfo(Model model) {
        MemberResDto memberResDto = memberService.readOne();

        Map<String, String> map = new HashMap<>();
        map.put("username", memberResDto.getUsername());
        map.put("nickname", memberResDto.getNickname());
        map.put("email", memberResDto.getEmail());
        model.addAllAttributes(map);

        return "memberInfo";
    }

    @PostMapping("/memberInfo")
    public String update(@Valid @ModelAttribute MemberUpdateReqDto memberUpdateReqDto,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        try {
            memberService.nicknameDuplicationcheck2(nickname);
            memberService.update(nickname, email);
        } catch (SameNicknameException e) {
            log.info(e.getMessage());
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
            return "redirect:/memberInfo";
        }

        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("nickname", nickname);
        map.put("email", email);
        model.addAllAttributes(map);
        return "memberInfo";
    }

    @GetMapping("/deleteMember")
    public String getDelete() {
        return "leave";
    }

    @PostMapping("/deleteMember")
    public String delete(String password, HttpSession httpSession, Model model) {

        boolean flag = memberService.delete(password, httpSession);

        if (flag)
            return "redirect:/home";
        else {
            model.addAttribute("res", "WRONG PASSWORD");
            return "leave";
        }
    }
}
