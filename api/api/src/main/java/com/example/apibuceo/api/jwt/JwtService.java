package com.example.apibuceo.api.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.apibuceo.api.models.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY= "secretkey";

    public String getToken(UserDetails usuario){
        return getToken(new HashMap<>(),usuario);
    }

    public String getToken(Map<String,Object> extraClaims,UserDetails usuario){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(usuario.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10)).signWith((Key) getkey(), SignatureAlgorithm.HS256).compact();

    }

    private Object getkey() {
        // TODO Auto-generated method stub
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
