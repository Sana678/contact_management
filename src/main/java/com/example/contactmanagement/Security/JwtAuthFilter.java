package com.example.contactmanagement.Security;

import java.io.IOException;


import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.contactmanagement.Repository.UserRepository;
import com.example.contactmanagement.Util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Component
public class JwtAuthFilter extends OncePerRequestFilter {


private final JwtUtil jwtUtil;
private final UserRepository userRepository;

public JwtAuthFilter(UserRepository userRepository,JwtUtil jwtUtil) {
    this.userRepository = userRepository;
    this.jwtUtil = jwtUtil;
}

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
throws ServletException, IOException {


final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
if (authHeader == null || !authHeader.startsWith("Bearer ")) {
filterChain.doFilter(request, response);
return;
}


final String token = authHeader.substring(7);
final String email = jwtUtil.extractEmail(token);
if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
// load user
var userOpt = userRepository.findByEmail(email);
if (userOpt.isPresent()) {
var user = userOpt.get();
var auth = new UsernamePasswordAuthenticationToken(
user.getEmail(),
null,
java.util.List.of(new SimpleGrantedAuthority("ROLE_" + (user.getRole() == null ? "USER" : user.getRole())))
);
SecurityContextHolder.getContext().setAuthentication(auth);
}
}


filterChain.doFilter(request, response);
}
}
