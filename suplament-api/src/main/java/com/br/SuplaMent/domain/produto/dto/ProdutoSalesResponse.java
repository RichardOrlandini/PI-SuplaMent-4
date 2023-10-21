package com.br.SuplaMent.domain.produto.dto;

import com.br.SuplaMent.domain.categoria.dto.CategoriaResponse;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorResponseDTO;
import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.domain.venda.dto.SalesProductResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoSalesResponse {

    private Long id;

    @JsonProperty("insertion_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime insertionDate;

    private String nome;

    private Integer qtd;

    private FornecedorResponseDTO fornecedor;

    private CategoriaResponse categoria;

    private List<String> sales;

    public static ProdutoSalesResponse of (Produto produto, List<String> sales) {
         return ProdutoSalesResponse
                 .builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .qtd(produto.getQtd())
                .insertionDate(produto.getInsertionDate())
                .fornecedor(FornecedorResponseDTO.of(produto.getFornecedor()))
                .categoria(CategoriaResponse.of(produto.getCategoria()))
                 .sales(sales)
                 .build();
    }
}
