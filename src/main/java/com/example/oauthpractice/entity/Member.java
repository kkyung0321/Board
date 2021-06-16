package com.example.oauthpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Member implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(min = 1, message = "이름은 한 글자 이상이어야 합니다.")
    private String username;

    @NotBlank
    @Length(min = 1, message = "비밀번호는 한 글자 이상이어야 합니다.")
    private String password;

    @NotBlank
    @Length(min = 1, message = "닉네임은 한 글자 이상이어야 합니다.")
    private String nickname;

    @NotBlank
    @Email
    @Length(min = 3, message = "Email 세 글자 이상이어야 합니다.")
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder.Default
    @OneToMany(mappedBy = "pMember", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "rMember", orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    @CreatedDate
    private LocalDate createdDate;

    public void updateMember(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(role.getRole()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
