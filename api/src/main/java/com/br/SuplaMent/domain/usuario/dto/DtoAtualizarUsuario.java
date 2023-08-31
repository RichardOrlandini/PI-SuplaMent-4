package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.endereco.dto.DtoEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoAtualizarUsuario(

        @NotNull
        Long id,
        @NotNull
        String nome,
        @NotBlank @Email
        String email,

        @NotBlank
        String senha,
        String telefone,

        @NotBlank
        Boolean grupo,
        @NotNull @Valid
        DtoEndereco endereco
        ) {
}


