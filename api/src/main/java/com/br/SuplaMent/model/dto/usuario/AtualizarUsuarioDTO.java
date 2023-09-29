package com.br.SuplaMent.model.dto.usuario;

import com.br.SuplaMent.model.dto.endereco.CadastroEnderecoDTO;
import com.br.SuplaMent.utils.UserRole;
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
