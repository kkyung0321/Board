package com.example.oauthpractice.service;

import com.example.oauthpractice.config.AuthenticationFacade;
import com.example.oauthpractice.dto.member.MemberResDto;
import com.example.oauthpractice.entity.Member;
import com.example.oauthpractice.exception.SameNicknameException;
import com.example.oauthpractice.exception.SameUsernameException;
import com.example.oauthpractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final AuthenticationFacade authenticationFacade;

    public void usernameDuplicationCheck(String username) throws SameUsernameException {

        if (memberRepository.countByUsernameContaining(username) != 0L)
            throw new SameUsernameException("username already exists");
        else ;
    }

    public void nicknameDuplicationCheck(String username) throws SameNicknameException {

        if (memberRepository.countByNicknameContaining(username) != 0L)
            throw new SameNicknameException("nickname already exists");
        else ;
    }

    public void nicknameDuplicationcheck2(String nickname) throws SameNicknameException {
        Member member = (Member) authenticationFacade.getAuthentication().getPrincipal();
        Member member1 = memberRepository.findByUsername(member.getUsername()).get();

        log.info("member1 :" + member1.getUsername() + " nickname :" + nickname);
        if (member1.getNickname().equals(nickname))
            return;
        else if (memberRepository.countByNicknameContaining(nickname) != 0L)
            throw new SameNicknameException("nickname already exists");
        else ;
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username is not correct : " + username));

        return member;
    }

    public void update(String nickname, String email) {
        Member member = (Member) authenticationFacade.getAuthentication().getPrincipal();
        Member member1 = memberRepository.findById(member.getId()).get();
        member1.updateMember(nickname, email);
    }

    public boolean delete(String password, HttpSession httpSession) {
        Member member = (Member) authenticationFacade.getAuthentication().getPrincipal();
        if (new BCryptPasswordEncoder().matches(password, member.getPassword())) {
            memberRepository.deleteById(member.getId());
            httpSession.invalidate();
            return true;
        } else
            return false;
    }

    public MemberResDto readOne() {
        Member member = (Member) authenticationFacade.getAuthentication().getPrincipal();
        Member member1 = memberRepository.findByUsername(member.getUsername()).get();
        MemberResDto memberResDto = MemberResDto.of(member1);

        return memberResDto;
    }
}
