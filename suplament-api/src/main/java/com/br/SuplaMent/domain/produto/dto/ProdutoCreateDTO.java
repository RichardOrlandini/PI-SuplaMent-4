package com.br.SuplaMent.domain.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProdutoCreateDTO {
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("qtd")
    private Integer qtd;

    @JsonProperty("fornecedor_id")
    private Long fornecedorId;

    @JsonProperty("categoria_id")
    private Long categoriaId;

    @JsonProperty("nomeImagem")
    private String nomeImagem;
}
