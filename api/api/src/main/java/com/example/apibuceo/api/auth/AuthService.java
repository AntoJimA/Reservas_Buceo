package com.example.apibuceo.api.auth;

import org.springframework.stereotype.Service;

import com.example.apibuceo.api.jwt.JwtService;
import com.example.apibuceo.api.models.Usuario;
import com.example.apibuceo.api.repository.UserRepositoryImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepositoryImpl userRepositoryImpl;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        Usuario usuario = new Usuario(registerRequest.getNombre(),registerRequest.getApellido(), registerRequest.getEmail(), registerRequest.getNivelBuceo(), registerRequest.getUsername(), registerRequest.getPassword());
        userRepositoryImpl.save(usuario);
        return AuthResponse.builder().token(jwtService.getToken(usuario)).build();
    }
}
