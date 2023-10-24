package com.br.SuplaMent.domain.produto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoStoqueDTO {

    private String salesId;
    private List<ProdutoQuantidadeDTO> produtos;
    private String transactionid;
}
