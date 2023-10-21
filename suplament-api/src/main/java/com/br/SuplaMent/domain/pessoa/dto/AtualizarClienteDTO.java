package com.br.SuplaMent.domain.pessoa.dto;

import jakarta.validation.constraints.NotNull;


public record AtualizarClienteDTO (
    @NotNull
    Long id,
    @NotNull
    boolean active,
    @NotNull
    String nome,
    @NotNull
    String senha


) {
    }
