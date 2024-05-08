package com.example.apibuceo.api.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @GetMapping("/save")
    public ResponseEntity<String> save() {
        return ResponseEntity.ok("Usuario guardado");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete() {
        return ResponseEntity.ok("Usuario eliminado");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update() {
        return ResponseEntity.ok("Usuario actualizado");
    }
    @GetMapping("/findAll")
    public ResponseEntity<String> findAll() {
        return ResponseEntity.ok("Usuarios encontrados");
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<String> findById() {
        return ResponseEntity.ok("Usuario encontrado");
    }
    
}
