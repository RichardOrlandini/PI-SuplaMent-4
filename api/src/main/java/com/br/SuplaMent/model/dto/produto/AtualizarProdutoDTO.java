package com.br.SuplaMent.model.dto.produto;



import jakarta.validation.constraints.NotNull;

public record AtualizarProdutoDTO (

    @NotNull
    Long id,
    @NotNull
    String nome,
    @NotNull
    String descri,
    @NotNull
    double valor,
    @NotNull
    int qtd


        ) {
    }