package org.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.dto.MemberLoginDto;
import org.project.dto.MemberRegisterDto;
import org.project.service.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@Valid @RequestBody MemberRegisterDto memberRegisterDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().toString());
        }
        memberService.register(memberRegisterDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginMember(@Valid @RequestBody MemberLoginDto memberLoginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().toString());
        }
        return  ResponseEntity.ok(memberService.login(memberLoginDto));
    }

}
