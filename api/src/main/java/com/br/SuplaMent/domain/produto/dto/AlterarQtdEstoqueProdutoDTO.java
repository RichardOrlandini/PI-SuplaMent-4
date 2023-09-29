package com.br.SuplaMent.domain.produto.dto;

import com.br.SuplaMent.domain.produto.Produto;

public record AlterarQtdEstoqueProdutoDTO(Long id, int qtd) {
    public AlterarQtdEstoqueProdutoDTO(Produto produto) {
        this(produto.getId(), (int) produto.getQtd());
    }
}
