package com.example.apibuceo.api.controllers;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.apibuceo.api.models.Role;
import com.example.apibuceo.api.models.Usuario;
import com.example.apibuceo.api.repository.UserRepositoryImpl;
import com.mysql.cj.protocol.Security;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class ControladorUsuario {
    @PostMapping("/hola")
    public String getMethodName() {
        return "Hola mundo";
    }

    

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @PostMapping("/save")
    public ResponseEntity<String> save(Usuario usuario) {
        userRepositoryImpl.save(usuario);
        return ResponseEntity.ok("Usuario guardado");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(int id) {
        userRepositoryImpl.delete(id);
        return ResponseEntity.ok("Usuario eliminado");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(int id, Usuario usuario) {
        return ResponseEntity.ok("Usuario actualizado");
    }
    @GetMapping("/findAll")
    public ResponseEntity<String> findAll() {
        userRepositoryImpl.findAll();
        return ResponseEntity.ok("Usuarios encontrados");
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<String> findById(int id) {
        userRepositoryImpl.findById(id);
        return ResponseEntity.ok("Usuario encontrado");
    }

    @GetMapping("/mydata")
    public ResponseEntity<String> mydata() {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = userRepositoryImpl.findByUsername(username);
        return ResponseEntity.ok(usuario.toString());
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestParam String password, @RequestParam String newPassword) {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        PasswordEncoder passwordEncoder = new BCryptPassworsEncoder();
        String encoded = passwordEncoder.encode(password);
        if(username.equals("admin")){
            return ResponseEntity.status(Response.SC_FORBIDDEN).body("No puedes cambiar la contraseña del usuario admin");
        }
        return null;
    }


    
    
}
