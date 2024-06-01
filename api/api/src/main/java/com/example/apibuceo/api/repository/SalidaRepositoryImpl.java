package com.example.apibuceo.api.repository;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.apibuceo.api.models.Salidas;
@Repository
public class SalidaRepositoryImpl implements SalidaRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Salidas> listarSalidas() {
        try {
            // Implementation code here
            String query = "SELECT * FROM salidas";
            List<Salidas> listaSalidas = jdbcTemplate.query(query, (rs, rowNum) -> new Salidas(rs.getDate("fecha"), rs.getString("hora"), rs.getInt("capacidad"), rs.getInt("plazasDisponibles")));
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
            String query = "INSERT INTO salidas (fecha, hora, capacidad, plazasDisponibles) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(query, salida.getFecha(), salida.getHora(), salida.getCapacidad(), salida.getPlazasDisponibles());
        } catch (Exception e) {
            // Implementation code here
            e.printStackTrace();
        }
   }
    
    @Override
    public void modificarSalida(int id,Salidas salida) {
        // Implementation code here
        try {
            String query = "UPDATE salidas SET fecha = ?, hora = ?, capacidad = ?, plazasDisponibles = ? WHERE id = ?";
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
            String query = "DELETE FROM salidas WHERE id = ?";
            jdbcTemplate.update(query, id);
        } catch (Exception e) {
            // Implementation code here
            e.printStackTrace();
        }
    }
}
