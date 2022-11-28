package com.example.interviewPrep.quiz.utils;

import com.example.interviewPrep.quiz.member.dto.Role;
import com.example.interviewPrep.quiz.errors.InvalidTokenException;
import com.example.interviewPrep.quiz.member.repository.TokenRepository;
import com.example.interviewPrep.quiz.member.repository.MemberRepository;
import com.example.interviewPrep.quiz.member.service.CustomUserDetailService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
@Component
@Slf4j
public class JwtUtil {


    // access 토큰 유효 시간 3m
    private final long accessTokenValidTime =  Duration.ofMinutes(3).toMillis();
    // 리프레시 토큰 유효시간 | 2주
    private final long refreshTokenValidTime = Duration.ofDays(14).toMillis();
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TokenRepository tokenRepository;

    private final Key key;
    public JwtUtil(@Value("${jwt.secret}") String secret){
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    public String createAccessToken(Long memberId, Role role){
        Date now = new Date();

        // claims.put("role", role);

        return Jwts.builder()
                .setId(Long.toString(memberId))
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidTime))
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(Long memberId, Role role){
        // claims 생성 및 payload 설정
        // claims.put("role", role);

        Date now = new Date();
        return Jwts.builder()
                .setId(Long.toString(memberId))
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .signWith(key)
                .compact();
    }

    // 어세스 토큰 헤더 설정
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("accessToken", "bearer "+ accessToken);
    }

    /*
    public void setBodyAccessToken(HttpServletResponse response, String accessToken) {
        response.setBody("accessToken", accessToken);
    }
    */

    // 리프레시 토큰 헤더 설정
    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("refreshToken", "bearer "+ refreshToken);
    }

    // Request의 Header에서 AccessToken 값을 가져옵니다. "authorization" : "token'
    public String resolveAccessToken(HttpServletRequest request) {
        if(request.getHeader("accessToken") != null )
            return request.getHeader("accessToken");
        return null;
    }
    // Request의 Header에서 RefreshToken 값을 가져옵니다. "authorization" : "token'
    public String resolveRefreshToken(HttpServletRequest request) {
        if(request.getHeader("refreshToken") != null )
            return request.getHeader("refreshToken");
        return null;
    }

    public Authentication getAuthentication(String token) {

        Long memberId = Long.valueOf(this.decode(token).getId());
        UserDetails userDetails = customUserDetailService.loadUserByUsername(Long.toString(memberId));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Claims decode(String token){

        if(token == null || token.isBlank()){
            throw new InvalidTokenException(token);
        }

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch(SignatureException e){
            throw new InvalidTokenException(token);
        }
    }


    public boolean validateToken(String token){

        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }


    public Claims getMemberIdFromToken(String token){

        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        }catch(ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public static Long getMemberId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        Long memberId = Long.parseLong(userDetails.getUsername());
        return memberId;
    }


    public boolean isCreatedTokenValid(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            log.error("유효하지 않은 Token입니다", e.getMessage());
            return false;
        }
    }

    // MemberId로 권한 정보 가져오기
    public Role getRole(Long memberId) {
        return memberRepository.findById(memberId).get().getRole();
    }

    public boolean existsRefreshToken(String token) {
        return tokenRepository.existsByRefreshToken(token);
    }

}
