package com.br.SuplaMent.domain.produto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoQuantidadeDTO {

    private Long produtoId;
    private Integer qtd;
}
