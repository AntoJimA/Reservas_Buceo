package com.example.apibuceo.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.apibuceo.api.models.Usuario;
@Repository
public class UserRepositoryImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void save(Usuario usuario) {
        try{
            String sql = "INSERT INTO usuarios (nombre, apellido, email, password,username,nivelBuceo) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, usuario.getNombre(), usuario.getApellido(), usuario.getEmail());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try{
            String sql = "DELETE FROM usuarios WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }   

    public void update(Usuario usuario) {
        try{
            String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ? WHERE id = ?, password = ?, username = ?, nivelBuceo = ?";
            jdbcTemplate.update(sql, usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getId(), usuario.getPassword(), usuario.getUserName(), usuario.getNivelBuceo());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Usuario> findAll() {
        try{
            String sql = "SELECT * FROM usuarios";
          //  List<Usuario>lista=jdbcTemplate.query(sql, (rs, rowNum) -> new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("password")));
            return null;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public Usuario findById(int id) {
        try{
            String sql = "SELECT * FROM usuarios WHERE id = ?";
           // Usuario user=jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("password")));
            return null;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}   