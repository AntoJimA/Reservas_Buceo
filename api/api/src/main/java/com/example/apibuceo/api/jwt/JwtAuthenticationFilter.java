package com.example.apibuceo.api.jwt;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
            final String token= getTokenFromRequest(request);

            if(token == null){
                filterChain.doFilter(request, response);
                return;
            }

            filterChain.doFilter(request, response);

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        // TODO Auto-generated method stub
        final String authHeather=request.getHeader(HttpHeathers.AUTHORIZATION);
        return null;
    }
    
}
