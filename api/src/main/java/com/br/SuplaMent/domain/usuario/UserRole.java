package com.br.SuplaMent.domain.usuario;

public enum UserRole {

    ADMIN("admin"),
    USER("usuario"),

    CLIENTE("cliente");
    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
