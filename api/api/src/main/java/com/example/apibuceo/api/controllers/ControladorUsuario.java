package com.example.apibuceo.api.controllers;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apibuceo.api.auth.PasswordChangeRequest;
import com.example.apibuceo.api.models.Role;
import com.example.apibuceo.api.models.Usuario;
import com.example.apibuceo.api.repository.UserRepositoryImpl;
import com.mysql.cj.protocol.Security;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Usuario> mydata() {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = userRepositoryImpl.findByUsername(username);
        System.out.println(usuario.toString());
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        String password = passwordChangeRequest.getPassword();
        String newPassword = passwordChangeRequest.getNewPassword();
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario usuario = userRepositoryImpl.findByUsername(username);
        if(passwordEncoder.matches(password, usuario.getPassword())|| username.equals("admin")){// si el password es correcto o es el admin el que lo cambia se cambia la contraseña.
            int id = usuario.getId();
            usuario.setPassword(passwordEncoder.encode(newPassword));
            Usuario newUsuario = new Usuario(usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getNivelBuceo(), usuario.getUserName(), usuario.getPassword(), Role.valueOf(usuario.getRole().name()));
            userRepositoryImpl.update(newUsuario,id);
            return ResponseEntity.ok("Contraseña cambiada");
        }else{
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).body("Contraseña incorrecta");
        }
    }

}
