package com.br.SuplaMent.domain.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroEnderecoDTO(
 ///dg
        String complemento,
        @NotBlank
        @NotNull(message = "Numero não pode ser vazio")
        String numero,
        @NotBlank
        @NotNull(message = "Logradouro não pode ser vazio")
        String logradouro,
        @NotBlank
        @NotNull(message = "Bairro não pode ser vazio")
        String bairro,
        @NotBlank
        @NotNull(message = "Cep não pode ser vazio")
        String cep,
        @NotBlank
        @NotNull(message = "Cidade não pode ser vazio")
        String cidade,

        @NotBlank
        @NotNull(message = "uf não pode ser vazio")
        String uf
    ) {
}

