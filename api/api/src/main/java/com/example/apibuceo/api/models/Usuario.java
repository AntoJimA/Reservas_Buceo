package com.example.apibuceo.api.models;
public class Usuario{
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String nivelBuceo;

    public Usuario(String nombre, String apellido, String email, String nivelBuceo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nivelBuceo = nivelBuceo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNivelBuceo() {
        return nivelBuceo;
    }

    public void setNivelBuceo(String nivelBuceo) {
        this.nivelBuceo = nivelBuceo;
    }

    public int getId() {
        return id;
    }   
    public void setId(int id) {
        this.id = id;
    }
}