package com.example.apibuceo.api.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Usuario implements UserDetails{
    private int id;
    private String userName;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String nivelBuceo;
    @Enumerated
    private Role role;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String email, String nivelBuceo,String userName, String password, Role role)  {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nivelBuceo = nivelBuceo;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public Usuario(int id, String nombre, String apellido, String email, String nivelBuceo,String userName, String password, Role role)  {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nivelBuceo = nivelBuceo;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", nivelBuceo=" + nivelBuceo + ", role=" + role + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public String getRoleString() {
        return role.name();
    }

    public static Role getRoleFromString(String role) {
        return Role.valueOf(role);
    }
}