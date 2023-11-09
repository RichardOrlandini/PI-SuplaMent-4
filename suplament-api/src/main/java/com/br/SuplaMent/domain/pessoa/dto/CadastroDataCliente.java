package com.br.SuplaMent.domain.pessoa.dto;


import jakarta.validation.Valid;


public record CadastroDataCliente(
        @Valid CadastroClienteDTO client,
        @Valid CadastroEnderecosDTO[] enderecos
) {
}