package com.br.SuplaMent.domain.pessoa.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;


public record CadastroDataClient(
        @Valid CadastroDataClient client,
        @Valid CadastroEnderecosDTO enderecos
) {
=======
}