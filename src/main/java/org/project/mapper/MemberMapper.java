package org.project.mapper;

import lombok.RequiredArgsConstructor;
import org.project.dto.MemberRegisterDto;
import org.project.entity.Member;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final PasswordEncoder passwordEncoder;

    public Member toEntity(MemberRegisterDto registerDto) {

        String password = passwordEncoder.encode(registerDto.getPassword());

        return Member.builder()
                .email(registerDto.getEmail())
                .phone(registerDto.getPhone())
                .username(registerDto.getUsername())
                .password(password)
                .build();
    }

}
