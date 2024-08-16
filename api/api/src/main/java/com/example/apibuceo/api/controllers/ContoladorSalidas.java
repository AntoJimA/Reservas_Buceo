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

import com.example.apibuceo.api.Requests.ApuntarseRequest;
import com.example.apibuceo.api.models.Reservas;
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
import org.springframework.web.bind.annotation.RequestParam;






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
    public ResponseEntity<String> apuntarseSalida(@RequestBody ApuntarseRequest apuntarseRequest, @PathVariable int id) {
        //TODO: process POST request
        int idUsuario=apuntarseRequest.getIdUsuario();
        if(estaApuntado(idUsuario, id)){
            return ResponseEntity.ok("El usuario ya esta apuntado a la salida");
        }
        if(salidaRepositoryImpl.plazasDisponibles(id)==0){
            return ResponseEntity.ok("No hay plazas disponibles");
        }
        salidaRepositoryImpl.apuntarseSalida(idUsuario, id);
        return ResponseEntity.ok("Usuario apuntado a la salida");
    }

    @PutMapping("/desapuntarseSalida/{id}")
    public ResponseEntity<String> desapuntarseSalida(@RequestBody ApuntarseRequest desaApuntarseRequest, @PathVariable int id) {
        int idUsuario=desaApuntarseRequest.getIdUsuario();
        if(!estaApuntado(idUsuario, id)){
            return ResponseEntity.ok("El usuario no esta apuntado a la salida");
        }
        salidaRepositoryImpl.desapuntarseSalida(idUsuario,id);
        return ResponseEntity.ok("Usuario desapuntado de la salida");
    }

    private boolean esAdmin(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario =usuarioRepositoryImpl.findByUsername(username);
        return usuario.getRole().equals(Role.ADMIN);
    }

    private boolean estaApuntado(int idUsuario, int idSalida){
        List<Reservas> reservas=salidaRepositoryImpl.listarSalidasUsuario(idUsuario,idSalida);
        if(reservas.size()>0){
            return true;
        }else{
            return false;
        }
    }

    @GetMapping("/verUsuariosApuntados/{id}")
    public List<Usuario> verUsuariosApuntados(@PathVariable int id) {
        return salidaRepositoryImpl.verUsuariosApuntados(id);
    }
    
}
