package com.example.apibuceo.api.jwt;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mysql.cj.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
            System.out.println("Estamos en JwtAuthenticationFilter en el metodo doFilterInternal");
            System.out.println("Request: "+request);
            final String token= getTokenFromRequest(request);
            final String username;

            if(token == null){
                filterChain.doFilter(request, response);
                return;
            }

            username=jwtService.getUsernameFromToken(token);

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                System.out.println("Estamos en el if de JwtAuthenticationFilter en el metodo doFilterInternal en el segundo if");
                UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                if(jwtService.isTokenValid(token,userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
    }

    @SuppressWarnings("deprecation")
    private String getTokenFromRequest(HttpServletRequest request) {
        System.out.println("request: "+request);
        System.out.println("Estamos en JwtAuthenticationFilter en el metodo getTokenFromRequest");
        // TODO Auto-generated method stub
        System.out.println("Token: "+request.getHeader(HttpHeaders.AUTHORIZATION));
        String authHeather=request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("Token: "+authHeather);
        if(!org.springframework.util.StringUtils.isEmpty(authHeather)&& authHeather.startsWith("Bearer "))
        {
            System.out.println("Estamos en el if.");
            return authHeather.substring(7);
        }
        System.out.println("Me voy a null");
        return null;
    }
    
}
