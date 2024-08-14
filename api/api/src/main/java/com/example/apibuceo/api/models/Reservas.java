package com.example.apibuceo.api.models;

public class Reservas {

    private int idUsuario;
    private int idSalida;

    public Reservas(int idUsuario, int idSalida) {
        this.idUsuario = idUsuario;
        this.idSalida = idSalida;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
    }
}
