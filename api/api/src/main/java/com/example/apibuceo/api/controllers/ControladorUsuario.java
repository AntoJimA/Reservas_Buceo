package com.example.apibuceo.api.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apibuceo.api.models.Usuario;
import com.example.apibuceo.api.repository.UserRepositoryImpl;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @GetMapping("/hola")

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
    
}
