package com.br.SuplaMent.domain.produto.dto;

import com.br.SuplaMent.domain.produto.Produto;

public record ListagemProdutoDTO (Long id,  String nome, Integer qtd, String nomeImagem, double valo) {

    public ListagemProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getQtd(), produto.getNomeImagem(), produto.getValor());
    }
}
