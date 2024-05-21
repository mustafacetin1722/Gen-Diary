package com.mustafa.gendiary.security;

import com.mustafa.gendiary.service.impl.UserCredentialsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserCredentialsServiceImpl userCredentialsService;
    public JwtAuthenticationFilter(JwtService jwtService,
                                   UserCredentialsServiceImpl userCredentialsService) {
        this.jwtService = jwtService;
        this.userCredentialsService = userCredentialsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    }
}
