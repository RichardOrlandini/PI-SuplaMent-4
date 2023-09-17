package com.br.SuplaMent.domain.cliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IncluirClienteRequest {
    private Long id;
    private Boolean active;
    private String nome;
    private String email;
    private String senha;

}