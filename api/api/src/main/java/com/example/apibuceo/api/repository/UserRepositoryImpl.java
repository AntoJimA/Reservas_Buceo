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
            String sql = "INSERT INTO Usuario (nombre, apellido, email, password,username,nivelBuceo,roll) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getPassword(), usuario.getUserName(), usuario.getNivelBuceo(),usuario.getRole().name());
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
        System.out.println("Estamos en UserRepositoryImpl en el metodo findById");
        try{
            String sql = "SELECT * FROM usuarios WHERE id = ?";
            Usuario user=jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("nivelBuceo"),Usuario.getRoleFromString(rs.getString("role")))).get(0);
            return user;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Usuario findByUsername(String username) {
        System.out.println("Estamos en UserRepositoryImpl en el metodo findByUsername");
        try{
            System.out.println("Estamos en UserRepositoryImpl en el metodo findByUsername. username: "+ username);
            String sql = "SELECT * FROM Usuario WHERE username = ?";
            System.out.println("Después de sql.");
            Usuario user = jdbcTemplate.query(sql,new UserRowMapper(), username).get(0);
            System.out.println("Esto es después de coger los datos. contraseña: "+ user.getPassword());
            return user;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
    }

    public void changePassword(String username, String password ,String newPassword) {
        try{
            String sql = "UPDATE usuarios SET password = ? WHERE username = ?";
            jdbcTemplate.update(sql, newPassword, username);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}   