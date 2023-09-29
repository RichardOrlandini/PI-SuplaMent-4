package com.br.SuplaMent.model.dto.usuario;

import com.br.SuplaMent.utils.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CadastroUsuarioDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @CPF @NotBlank String cpf,

        @NotNull UserRole role

) {
}
