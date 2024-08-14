package com.example.apibuceo.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.apibuceo.api.models.Role;
import com.example.apibuceo.api.models.Salidas;
import com.example.apibuceo.api.models.Usuario;
import com.example.apibuceo.api.repository.SalidaRepositoryImpl;
import com.example.apibuceo.api.repository.UserRepositoryImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/salidas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ContoladorSalidas {
    
    @Autowired
    SalidaRepositoryImpl salidaRepositoryImpl;

    @Autowired
    UserRepositoryImpl usuarioRepositoryImpl;

    @GetMapping("/hola")    
    public String helloSalidas() {
        return "Hola salidas";  
    }

    @GetMapping("/getSalidas")
    public ResponseEntity<List<Salidas>> getSalidas() {
       List<Salidas>salidas=salidaRepositoryImpl.listarSalidas();
       return ResponseEntity.ok(salidas);
    }

    @PostMapping("/saveSalida")
    public ResponseEntity<String> crearSalida(@RequestBody Salidas salida) {
        //TODO: process POST request
        if(esAdmin()){
            salidaRepositoryImpl.crearSalida(salida);
            return ResponseEntity.ok("Salida guardada");
        }else{
            return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PutMapping("updateSalida/{id}")
    public ResponseEntity<String> modificarSalida(@PathVariable int id, @RequestBody Salidas salida) {
        //TODO: process PUT request
        if(!esAdmin()){
            return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }else{
            if(!salidaRepositoryImpl.existeSalida(id)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            salidaRepositoryImpl.modificarSalida(id, salida);
            return ResponseEntity.ok("Salida modificada");
        }       
    }

    @DeleteMapping("/deleteSalida/{id}")
    public ResponseEntity<String> eliminarSalida(@PathVariable int id) {
        if(!esAdmin()){
            return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }else{
            salidaRepositoryImpl.eliminarSalida(id);
            return ResponseEntity.ok("Salida eliminada");
        }
    }

    @PostMapping("/apuntarseSalida/{id}")
    public ResponseEntity<String> apuntarseSalida(@RequestBody String entity) {
        //TODO: process POST request
        
        return null;
    }

    @PostMapping("/desapuntarseSalida/{id}")
    public ResponseEntity<String> desapuntarseSalida(@RequestBody String entity) {
        return null;
    }
    




    private boolean esAdmin(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario =usuarioRepositoryImpl.findByUsername(username);
        return usuario.getRole().equals(Role.ADMIN);
    }
    
    

}
