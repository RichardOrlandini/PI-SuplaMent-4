package com.br.SuplaMent.domain.produto.dto;

//import com.br.SuplaMent.domain.produto.Categorias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroProdutoDTO (

    @NotBlank String nome,
    @NotBlank String descri,
    @NotNull double valor,
    @NotNull int qtd,

    @NotBlank String nomeImagem

   // @NotBlank Categorias categoria
){
    }