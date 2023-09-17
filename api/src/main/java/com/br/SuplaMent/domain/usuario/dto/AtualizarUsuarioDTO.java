package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.endereco.dto.CadastroEnderecoDTO;
import com.br.SuplaMent.domain.usuario.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AtualizarUsuarioDTO(
        @NotNull
        Long id,
        @NotNull
        String nome,
        @NotNull
        @Email
        String email,
        @NotNull
        String senha,
        String telefone,
        @NotNull
        Boolean grupo,
        @NotNull @Valid
        CadastroEnderecoDTO endereco,

        @NotNull UserRole role
        ) {
}
