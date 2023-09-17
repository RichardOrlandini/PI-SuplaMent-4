package com.br.SuplaMent.domain.produto.dto;



import com.br.SuplaMent.domain.produto.dto.CadastroProdutoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
@JsonIgnoreProperties(ignoreUnknown = true)
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