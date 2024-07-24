package org.project.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // application.yml 파일에서 비밀 키를 읽어옵니다.
    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    private final long JWT_EXPIRATION = 1000 * 60 * 60 * 3; // 3시간

    // 사용자 정보로부터 JWT 토큰을 생성합니다.
    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return createToken(username);
    }

    // JWT 토큰을 생성합니다.
    private String createToken(String subject) {
        return Jwts.builder()
                .setSubject(subject) // 토큰의 주제 설정
                .setIssuedAt(new Date(System.currentTimeMillis())) // 토큰 발급 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION)) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 비밀 키로 서명
                .compact(); // 토큰 생성
    }

    // 토큰에서 사용자 이름을 추출합니다.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 토큰에서 만료일을 추출합니다.
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 토큰에서 특정 클레임을 추출합니다.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 모든 클레임을 추출합니다.
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // 토큰이 만료되었는지 확인합니다.
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 토큰이 유효한지 검증합니다.
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
