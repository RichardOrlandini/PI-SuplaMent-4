package com.br.SuplaMent.domain.pessoa.dto;

import com.br.SuplaMent.domain.endereco.dto.CadastroEnderecoDTO;
import com.br.SuplaMent.domain.pessoa.UserRole;
import jakarta.validation.Valid;
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
        String telefone,
        @NotNull
        Boolean grupo,
        @NotNull @Valid
        CadastroEnderecoDTO endereco,

        @NotNull UserRole role,
        @CPF @NotBlank String cpf
) {
}
