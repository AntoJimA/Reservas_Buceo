package com.example.apibuceo.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.apibuceo.api.models.Usuario;

public class UserRowMapper implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        System.out.println("Estamos en UserRowMapper en el metodo mapRow");
        System.out.println(rs.getString("nombre"));
        Usuario user = new Usuario();
        user.setId(rs.getInt("id"));
        user.setNombre(rs.getString("nombre"));
        user.setApellido(rs.getString("apellido"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setUserName(rs.getString("username"));
        user.setNivelBuceo(rs.getString("nivelBuceo"));
        user.setRole(Usuario.getRoleFromString(rs.getString("roll")));
        return user;
    }
    
}
