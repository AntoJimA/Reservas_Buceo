package com.example.apibuceo.api.models;

import java.sql.Date;

public class Salidas {
    private int id;
    private Date fecha;
    private String hora;
    private int capacidad;
    private int plazasDisponibles;

    public Salidas() {
    }
    
    public Salidas(Date fecha, String hora, int capacidad, int plazasDisponibles) {
        this.fecha = fecha;
        this.hora = hora;
        this.capacidad = capacidad;
        this.plazasDisponibles = capacidad;
    }

    public Salidas(int id,Date fecha, String hora, int capacidad, int plazasDisponibles) {
        this.fecha = fecha;
        this.hora = hora;
        this.capacidad = capacidad;
        this.plazasDisponibles = capacidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
}
