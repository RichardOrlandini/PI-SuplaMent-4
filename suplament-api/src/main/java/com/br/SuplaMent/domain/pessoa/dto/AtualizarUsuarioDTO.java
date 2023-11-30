package com.br.SuplaMent.domain.pessoa.dto;

import com.br.SuplaMent.domain.pessoa.UserRole;
import jakarta.validation.constraints.NotNull;

public record AtualizarUsuarioDTO(
        @NotNull
        Long id,
        @NotNull
        boolean active,
        @NotNull
        String nome,
        @NotNull
        String senha,
        @NotNull UserRole role
) {
}
