package com.br.SuplaMent.domain.pessoa;

public enum UserRole {
    ADMIN("ADMIN"),

    CLIENT("CLIENTE"),
    ESTOQUISTA("ESTOQUISTA");

    private String role;
    UserRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
