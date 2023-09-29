package com.br.SuplaMent.utils;

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



    public boolean contains(String cliente) {
        return  false;
    }
}
