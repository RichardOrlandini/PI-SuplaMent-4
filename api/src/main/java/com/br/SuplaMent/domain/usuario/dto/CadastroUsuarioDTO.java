package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.usuario.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotNull UserRole role
) {
}
