package com.br.SuplaMent.domain.pessoa;

public enum UserRole {
    ADMIN("admin"),
    ESTOQUISTA("estoquista");
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