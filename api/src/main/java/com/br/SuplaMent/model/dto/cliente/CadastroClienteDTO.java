package com.br.SuplaMent.model.dto.cliente;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CadastroClienteDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha



) {
}
