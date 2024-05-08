package com.example.apibuceo.api.repository;

import com.example.apibuceo.api.models.Usuario;

public interface UserRepository {
    void save(Usuario usuario);
    void delete(Usuario usuario);
    void update(Usuario usuario);
    void findAll();
    void findById(int id);  
}
