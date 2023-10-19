package com.br.SuplaMent.domain.produto.dto;

import com.br.SuplaMent.domain.categoria.dto.CategoriaResponse;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorResponseDTO;
import com.br.SuplaMent.domain.produto.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseToSalesDTO {
    private Long id;

    @JsonProperty("insertion_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime insertionDate;

    private String nome;

    private Integer qtd;

    private FornecedorResponseDTO fornecedor;

    private CategoriaResponse categoria;

    private String nomeImagem;

    public static ProdutoResponseToSalesDTO of(Produto produto) {
        return ProdutoResponseToSalesDTO
                .builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .qtd(produto.getQtd())
                .insertionDate(produto.getInsertionDate())
                .fornecedor(FornecedorResponseDTO.of(produto.getFornecedor()))
                .categoria(CategoriaResponse.of(produto.getCategoria()))
                .build();


    }
}

//        var response = new ProdutoResponseToSalesDTO();
//

