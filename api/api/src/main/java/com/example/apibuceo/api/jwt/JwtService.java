package com.example.apibuceo.api.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.apibuceo.api.models.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY= "9f8b7c6d5a4e3f2b1a0d9c8b7f6e5d4c3b2a1d0e9f8b7c6d5a4e3f2b1a0d9c";

    public String getToken(UserDetails usuario){
        return getToken(new HashMap<>(),usuario);
    }

    public String getToken(Map<String,Object> extraClaims,UserDetails usuario){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(usuario.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10)).signWith(getkey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getkey() {
        // TODO Auto-generated method stub
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        System.out.println("Estamos en getKeys");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        // TODO Auto-generated method stub
        return getClaim(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        // TODO Auto-generated method stub
        final String username= getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));}

    private Claims getAllClaims(String token){
        System.out.println("Estamos en getAllClaims");
        return Jwts.parserBuilder().setSigningKey(getkey()).build().parseClaimsJws(token).getBody();
    }

    private <T> T getClaim(String token, Function<Claims,T> claimsResolver){

        final Claims claims= getAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token){
        return getClaim(token,Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

    
}
