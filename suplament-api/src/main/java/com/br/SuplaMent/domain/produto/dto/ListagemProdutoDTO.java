package com.br.SuplaMent.domain.produto.dto;

import com.br.SuplaMent.domain.produto.Produto;

public record ListagemProdutoDTO (Long id,  String nome, int qtd) {

    public ListagemProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), (Integer) produto.getQtd());
    }

}
