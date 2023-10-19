package com.br.SuplaMent.domain.produto.dto;

import com.br.SuplaMent.domain.categoria.dto.CategoriaResponse;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProdutoCreateToSalesDTO {
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
