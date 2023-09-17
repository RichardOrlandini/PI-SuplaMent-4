package com.br.SuplaMent.domain.usuario;

public enum UserRole {
    ADMIN("admin"),
    ESTOQUISTA("estoquista"),
    CLIENTE("cliente");
    private String role;
    UserRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
