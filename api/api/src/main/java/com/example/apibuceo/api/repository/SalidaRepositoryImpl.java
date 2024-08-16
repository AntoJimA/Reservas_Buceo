package com.example.apibuceo.api.repository;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.apibuceo.api.models.Reservas;
import com.example.apibuceo.api.models.Salidas;
import com.example.apibuceo.api.models.Usuario;
@Repository
public class SalidaRepositoryImpl implements SalidaRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Salidas> listarSalidas() {
        try {
            // Implementation code here
            String query = "SELECT * FROM Salida";
            List<Salidas> listaSalidas = jdbcTemplate.query(query, (rs, rowNum) -> new Salidas(rs.getDate("fecha"), rs.getString("hora"), rs.getInt("capacidad"), rs.getInt("plazasDisponiles")));
            return listaSalidas;
        } catch (Exception e) {
            // Implementation code here
            e.printStackTrace();
        }
        return null;
        // Implementation code here
    }
    
    @Override
    public void crearSalida(Salidas salida) {
        // Implementation code here
        try {
            String sql = "INSERT INTO Salida (fecha, hora, capacidad, plazasDisponiles) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, salida.getFecha(), salida.getHora(), salida.getCapacidad(), salida.getPlazasDisponibles());
        } catch (Exception e) {
            // Implementation code here
            e.printStackTrace();
        }
   }
    
    @Override
    public void modificarSalida(int id,Salidas salida) {
        // Implementation code here
        try {
            String query = "UPDATE salida SET fecha = ?, hora = ?, capacidad = ?, plazasDisponiles = ? WHERE id = ?";
            jdbcTemplate.update(query, salida.getFecha(), salida.getHora(), salida.getCapacidad(), salida.getPlazasDisponibles(), id);
        } catch (Exception e) {
            // Implementation code here
            e.printStackTrace();
        }
    }
    
    @Override
    public void eliminarSalida(int id) {
        // Implementation code here
        try {
            String query = "DELETE FROM salida WHERE id = ?";
            jdbcTemplate.update(query, id);
        } catch (Exception e) {
            // Implementation code here
            e.printStackTrace();
        }
    }

    public boolean existeSalida(int id) {
        // Implementation code here
        try {
            String query = "SELECT * FROM salida WHERE id = ?";
            List<Salidas> listaSalidas = jdbcTemplate.query(query, (rs, rowNum) -> new Salidas(rs.getDate("fecha"), rs.getString("hora"), rs.getInt("capacidad"), rs.getInt("plazasDisponiles")), id);
            if (listaSalidas.size() > 0) {
                return true;
            }
        } catch (Exception e) {
            // Implementation code here
            e.printStackTrace();
        }
        return false;
    }


    public void apuntarseSalida(int idUsuariomint,int idSalida){
        try{
            int plazasDisponibles=plazasDisponibles(idSalida);
            plazasDisponibles--;
            modificarPlazasDisponibles(idSalida, plazasDisponibles);
            String sql = "INSERT INTO Reserva (id_usuario, id_salida) VALUES (?, ?)";
            jdbcTemplate.update(sql, idUsuariomint, idSalida);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void desapuntarseSalida(int idUsuariomint,int idSalida){
        try{
            String sql = "DELETE FROM Reservas WHERE id_usuario = ? AND id_salida = ?";
            int plazasDisponibles=plazasDisponibles(idSalida);
            plazasDisponibles++;
            modificarPlazasDisponibles(idSalida, plazasDisponibles);
            jdbcTemplate.update(sql, idUsuariomint, idSalida);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Reservas> listarSalidasUsuario(int idUsuario,int idSalida){
        try{
            String sql = "SELECT id_salida FROM Reserva WHERE id_usuario = ?";
            List<Reservas>listaSalidas=jdbcTemplate.query(sql, new Object[]{idUsuario}, (rs, rowNum) -> new Reservas(rs.getInt("id_usuario"), rs.getInt("id_salida")));
            return listaSalidas;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
    }

    public int plazasDisponibles(int idSalida){
        try{
            String sql = "SELECT plazasDisponiles FROM salida WHERE id = ?";
            int plazasDisponibles=jdbcTemplate.queryForObject(sql, new Object[]{idSalida}, Integer.class);
            return plazasDisponibles;
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    private void modificarPlazasDisponibles(int idSalida,int plazasDisponibles){
        try{
            String sql = "UPDATE salida SET plazasDisponiles = ? WHERE id = ?";
            jdbcTemplate.update(sql, plazasDisponibles, idSalida);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Usuario> verUsuariosApuntados(int idSalida) {
        String sql = "SELECT * FROM Usuario WHERE id IN (SELECT id_usuario FROM Reserva WHERE id_salida = ?)";

        try {
            List<Usuario> listaUsuarios = jdbcTemplate.query(sql, new Object[]{idSalida}, usuarioRowMapper());
            return listaUsuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private RowMapper<Usuario> usuarioRowMapper() {
        return (rs, rowNum) -> new Usuario(
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("username"),
            rs.getString("nivelBuceo"),
            Usuario.getRoleFromString(rs.getString("roll"))
        );
    }

}
