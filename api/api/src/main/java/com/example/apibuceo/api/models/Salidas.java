package com.example.apibuceo.api.models;

public class Salidas {
    private String fecha;
    private String hora;
    private int capacidad;
    private int plazasDisponibles;

    public Salidas(String fecha, String hora, int capacidad, int plazasDisponibles) {
        this.fecha = fecha;
        this.hora = hora;
        this.capacidad = capacidad;
        this.plazasDisponibles = plazasDisponibles;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
}
