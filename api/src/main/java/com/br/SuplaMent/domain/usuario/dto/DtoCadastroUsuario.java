package com.br.SuplaMent.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoCadastroUsuario(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha
) {
}
