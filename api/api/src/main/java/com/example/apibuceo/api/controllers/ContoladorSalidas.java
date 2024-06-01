package com.example.apibuceo.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.apibuceo.api.models.Salidas;
import com.example.apibuceo.api.repository.SalidaRepositoryImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
@RequestMapping("/salidas")
public class ContoladorSalidas {
    
    @Autowired
    SalidaRepositoryImpl salidaRepositoryImpl;

    public String helloSalidas() {
        return "Hola salidas";  
    }

    @GetMapping("/getSalidas")
    public ResponseEntity<String> getSalidas() {
       salidaRepositoryImpl.listarSalidas();
       return ResponseEntity.ok("Salidas encontradas");
    }

    @PostMapping("/saveSalida")
    public ResponseEntity<String> crearSalida(@RequestBody Salidas salida) {
        //TODO: process POST request
        salidaRepositoryImpl.crearSalida(salida);
        return ResponseEntity.ok("Salida guardada");
    }

    @PutMapping("updateSalida/{id}")
    public ResponseEntity<String> modificarSalida(@PathVariable int id, @RequestBody Salidas salida) {
        //TODO: process PUT request
        salidaRepositoryImpl.modificarSalida(id, salida);
        return ResponseEntity.ok("Salida actualizada");
    }

    @DeleteMapping("/deleteSalida/{id}")
    public ResponseEntity<String> eliminarSalida(@PathVariable int id) {
        salidaRepositoryImpl.eliminarSalida(id);
        return ResponseEntity.ok("Salida eliminada");
    }
    
    

}
