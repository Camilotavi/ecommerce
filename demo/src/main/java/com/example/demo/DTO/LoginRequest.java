package com.example.demo.DTO;

public class LoginRequest {
    private String email;
    private String password;

    // 👇 Getters y Setters obligatorios para que Spring pueda mapear el JSON
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

