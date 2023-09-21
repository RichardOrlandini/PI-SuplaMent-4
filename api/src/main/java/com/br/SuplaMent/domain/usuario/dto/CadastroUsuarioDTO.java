package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.usuario.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CadastroUsuarioDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotNull UserRole role,
        @CPF @NotBlank String cpf
) {
}
