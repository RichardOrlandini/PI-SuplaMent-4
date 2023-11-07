package com.br.SuplaMent.domain.cliente.dto;


import com.br.SuplaMent.domain.usuario.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


public record CadastroClienteDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
<<<<<<< Updated upstream:api/src/main/java/com/br/SuplaMent/domain/cliente/dto/CadastroClienteDTO.java
        @NotBlank String senha



) {
=======
        @NotBlank String senha,

        @NotBlank String genero,

        @NotBlank Date dataNascimento
        ) {
>>>>>>> Stashed changes:suplament-api/src/main/java/com/br/SuplaMent/domain/pessoa/dto/CadastroClienteDTO.java
}
