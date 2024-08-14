package com.example.apibuceo.api.repository;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
            String sql = "INSERT INTO Reservas (id_usuario, id_salida) VALUES (?, ?)";
            jdbcTemplate.update(sql, idUsuariomint, idSalida);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void desapuntarseSalida(int idUsuariomint,int idSalida){
        try{
            String sql = "DELETE FROM Reservas WHERE id_usuario = ? AND id_salida = ?";
            jdbcTemplate.update(sql, idUsuariomint, idSalida);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
