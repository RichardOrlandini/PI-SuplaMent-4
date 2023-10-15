package com.br.SuplaMent.domain.cliente.dto;

import com.br.SuplaMent.domain.endereco.dto.CadastroEnderecoDTO;
import com.br.SuplaMent.domain.usuario.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
