package com.br.SuplaMent.domain.cliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AtualizarClienteRequest {
    private Long id;
    private Boolean active;
    private String nome;
    private String email;
    private String senha;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}