package com.example.OnlineStoreProject.security.jwt;

import com.example.OnlineStoreProject.models.enums.Role;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;

        //header: Authorization Bearer [jwt]
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
        }



        if (jwtToken != null && JwtUtil.isTokenValid(jwtToken)) {


            Claims claims = JwtUtil.getClaimsFromToken(jwtToken);
            String username = claims.getSubject();
            Role role = JwtUtil.getRoleFromToken(jwtToken);

            List<GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + role.name()) // важно для hasRole
            );

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    JwtUtil.getClaimsFromToken(jwtToken).getSubject(),
                    null, //verified credentials when calling isTokenValid()
                     authorities//roles & authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
