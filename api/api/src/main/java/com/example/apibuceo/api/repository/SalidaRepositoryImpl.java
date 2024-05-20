package com.example.apibuceo.api.repository;

import java.util.List;

import com.example.apibuceo.api.models.Salidas;

public class SalidaRepositoryImpl implements SalidaRepository {
    
    @Override
    public List<Salidas> listarSalidas() {
        try {
            // Implementation code here
            String query = "SELECT * FROM salidas";
            
        } catch (Exception e) {
            // Implementation code here
        }
        return null;
        // Implementation code here
    }
    
    @Override
    public void crearSalida(Salidas salida) {
        // Implementation code here
    }
    
    @Override
    public void modificarSalida(int id,Salidas salida) {
        // Implementation code here
    }
    
    @Override
    public void eliminarSalida(int id) {
        // Implementation code here
    }
}
