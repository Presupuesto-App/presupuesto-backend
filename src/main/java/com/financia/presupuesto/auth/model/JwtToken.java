package com.financia.presupuesto.auth.model;

public class JwtToken {
    private String token;

    public JwtToken() {}

    public JwtToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

