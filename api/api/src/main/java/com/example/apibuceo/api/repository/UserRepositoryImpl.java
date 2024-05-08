package com.example.apibuceo.api.repository;

import com.example.apibuceo.api.models.Usuario;

public class UserRepositoryImpl {
    public void save(Usuario usuario) {
        System.out.println("Usuario guardado");
    }

    public void delete(Usuario usuario) {
        System.out.println("Usuario eliminado");
    }   

    public void update(Usuario usuario) {
        System.out.println("Usuario actualizado");
    }

    public void findAll() {
        System.out.println("Usuarios encontrados");
    }

    public void findById(int id) {
        System.out.println("Usuario encontrado");
    }
}
