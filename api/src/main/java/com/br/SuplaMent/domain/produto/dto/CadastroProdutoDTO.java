package com.br.SuplaMent.domain.produto.dto;

//import com.br.SuplaMent.domain.produto.Categorias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
@JsonIgnoreProperties(ignoreUnknown = true)
public record CadastroProdutoDTO (

    @NotBlank String nome,
    @NotBlank String descri,
    @NotBlank double valor,
    @NotBlank int qtd
   // @NotBlank Categorias categoria
){

    }