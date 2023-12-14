package com.br.SuplaMent.domain.pessoa.dto;


import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.endereco.dto.CadastroEnderecoDTO;
import com.br.SuplaMent.domain.pessoa.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;


public record CadastroClienteDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotBlank String genero,
        @NotBlank Date dataNascimento,
        @NotBlank @CPF String cpf,

        @NotNull UserRole role
) { }
