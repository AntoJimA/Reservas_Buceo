package com.example.apibuceo.api.auth;

public class PasswordChangeRequest {
    
    private String password;
    private String newPassword;
    
    // Getters y Setters
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNewPassword() {
        return newPassword;
    }
    
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    
}
