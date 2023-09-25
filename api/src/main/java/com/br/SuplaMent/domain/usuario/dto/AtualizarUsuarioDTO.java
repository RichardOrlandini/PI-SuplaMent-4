package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.endereco.dto.CadastroEnderecoDTO;
import com.br.SuplaMent.domain.usuario.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record AtualizarUsuarioDTO(

        @NotNull
        Long id,
        @NotNull
        boolean active,
        @NotNull
        String nome,
        @NotNull
        String senha,
        @CPF  String cpf,
        String telefone,

        Boolean grupo,

        @NotNull UserRole role
//        @Valid
//        CadastroEnderecoDTO endereco

        ) {
}
