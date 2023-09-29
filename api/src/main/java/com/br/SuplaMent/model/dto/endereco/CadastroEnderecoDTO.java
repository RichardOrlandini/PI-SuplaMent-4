package com.br.SuplaMent.model.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroEnderecoDTO(

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
        String cep
    ) {
}

