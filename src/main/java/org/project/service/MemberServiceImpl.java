package org.project.service;

import lombok.RequiredArgsConstructor;
import org.project.dto.MemberLoginDto;
import org.project.dto.MemberRegisterDto;
import org.project.entity.Member;
import org.project.exception.CustomException;
import org.project.exception.ErrorCode;
import org.project.mapper.MemberMapper;
import org.project.repository.MemberRepository;
import org.project.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final MyUserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public void register(MemberRegisterDto registerDto) {
        memberRepository.findById(registerDto.getEmail())
                .ifPresent(member -> {
                    throw new CustomException(ErrorCode.MEMBER_EMAIL_EXCEPTION);
                });

        Member member = memberMapper.toEntity(registerDto);
        memberRepository.save(member);
    }

    @Override
    public String login(MemberLoginDto loginDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_LOGIN_CREDENTIALS);
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }
}
