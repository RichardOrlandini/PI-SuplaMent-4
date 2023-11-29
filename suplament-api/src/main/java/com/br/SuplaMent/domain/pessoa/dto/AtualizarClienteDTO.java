package com.br.SuplaMent.domain.pessoa.dto;


import com.br.SuplaMent.domain.endereco.dto.CadastroEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;


public record AtualizarClienteDTO (
        @NotNull
        Long id,
        @NotNull
        boolean active,
        @NotNull
        String nome,
        @NotNull
        String senha,

   @NotNull @Valid
        CadastroEnderecoDTO endereco,

        @NotBlank String genero,
        @NotNull Date dataNascimento



) {
    }
