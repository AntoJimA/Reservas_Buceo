package com.example.apibuceo.api.repository;

import java.sql.Date;
import java.util.List;

import com.example.apibuceo.api.models.Salidas;

public interface SalidaRepository {
    public void crearSalida(Salidas salida);
    public void eliminarSalida(int id);
    public void modificarSalida(int id, Salidas salida);
    public List<Salidas> listarSalidas();
}
