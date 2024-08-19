package com.example.apibuceo.api.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest loginRequest) {
        System.out.println("Estamos en AuthService en el metodo login");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        System.out.println("Estamos en AuthService en el metodo login despues de authenticate");
        UserDetails usuario = userRepositoryImpl.findByUsername(loginRequest.getUsername());
        String token = jwtService.getToken(usuario);
        return new AuthResponse.AuthResponseBuilder().token(token).build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
       // System.out.println("Estamos en AuthService en el metodo register");
        //System.out.println(registerRequest);
        Usuario aux = userRepositoryImpl.findByUsername(registerRequest.getUsername());
        if(aux != null) {
            return AuthResponse.builder().token("Usuario ya existe").build();
        }
        Usuario usuario = new Usuario(registerRequest.getNombre(),registerRequest.getApellido(), registerRequest.getEmail(), registerRequest.getNivelBuceo(), registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()), Usuario.getRoleFromString(registerRequest.getRole()));
        userRepositoryImpl.save(usuario);
        return AuthResponse.builder().token(jwtService.getToken(usuario)).build();
    }
}
