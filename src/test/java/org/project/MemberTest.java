package org.project;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.project.dto.MemberLoginDto;
import org.project.dto.MemberRegisterDto;
import org.project.service.MemberService;
import org.project.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @Transactional
    public void 회원가입_성공(){
        MemberRegisterDto memberRegisterDto = new MemberRegisterDto("test@naver.com","010-0000-0000","test","Test12345@@");
        memberService.register(memberRegisterDto);
    }

    @Test
    public void 로그인용_회원가입() {
        MemberRegisterDto memberRegisterDto = new MemberRegisterDto("loginTest@naver.com","010-0000-0000","Login","Test12345@@");
        memberService.register(memberRegisterDto);
    }

    @Test
    public void 로그인_성공() {
        MemberLoginDto memberLoginDto = new MemberLoginDto("loginTest@naver.com", "Test12345@@");
        String jwt = memberService.login(memberLoginDto);
        System.out.println("JWT Token: " + jwt);

        assertNotNull(jwt, "JWT 토큰은 null이 아니어야 합니다.");

        Claims claims = jwtUtil.extractAllClaims(jwt);

        // 클레임 검증
        assertEquals("loginTest@naver.com", claims.getSubject(), "JWT 토큰의 주제는 사용자 이름과 일치해야 합니다.");
        assertNotNull(claims.getIssuedAt(), "JWT 토큰의 발급 날짜는 null이 아니어야 합니다.");
        assertNotNull(claims.getExpiration(), "JWT 토큰의 만료 날짜는 null이 아니어야 합니다.");
    }

}
