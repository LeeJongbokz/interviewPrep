package com.example.interviewPrep.quiz.filter;

import com.example.interviewPrep.quiz.member.dto.Role;
import com.example.interviewPrep.quiz.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 JWT 를 받아옵니다.
        String accessToken = jwtUtil.resolveAccessToken(request);
        String refreshToken = jwtUtil.resolveRefreshToken(request);
        // 유효한 토큰인지 확인합니다.
        if (accessToken != null) {
            // access 토큰이 유효한 상황
            if (jwtUtil.validateToken(accessToken)) {
                this.setAuthentication(accessToken);
            }
            // access 토큰이 만료된 상황 | 리프레시 토큰 또한 존재하는 상황
            else if (!jwtUtil.validateToken(accessToken) && refreshToken != null) {
                // 재발급 후, 컨텍스트에 다시 넣기
                /// 리프레시 토큰 검증

                boolean validateRefreshToken = jwtUtil.validateToken(refreshToken);


                Claims claims = jwtUtil.getMemberIdFromToken(refreshToken);
                Long memberId = Long.parseLong(claims.getId());
                Role role = jwtUtil.getRole(memberId);

                /// 리프레시 토큰 저장소 존재유무 확인
                boolean isRefreshToken = jwtUtil.existsRefreshToken(refreshToken);

                if (!(validateRefreshToken && isRefreshToken)) {
                    String newRefreshToken = jwtUtil.createRefreshToken(memberId, role);
                    jwtUtil.setHeaderRefreshToken(response, newRefreshToken);
                }

                /// 토큰 발급
                String newAccessToken = jwtUtil.createAccessToken(memberId, role);
                System.out.println("newAccessToken은?" + newAccessToken);
                /// 헤더에 어세스 토큰 추가
                jwtUtil.setHeaderAccessToken(response, newAccessToken);
                /// 컨텍스트에 넣기
                this.setAuthentication(newAccessToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    // SecurityContext 에 Authentication 객체를 저장합니다.
    public void setAuthentication(String token) {
        // 토큰으로부터 유저 정보를 받아옵니다.
        Authentication authentication = jwtUtil.getAuthentication(token);
        // SecurityContext 에 Authentication 객체를 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
