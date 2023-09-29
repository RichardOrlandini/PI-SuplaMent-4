package com.br.SuplaMent.model.dto.produto;

import com.br.SuplaMent.model.Produto;

public record ListagemProdutoDTO (Long id, Boolean ativo, String nome, String descri, double valor, int qtd) {
    public ListagemProdutoDTO(Produto produto) {
        this(produto.getId(),produto.getActive(), produto.getNome(), produto.getDescri(), produto.getValor(), (int) produto.getQtd());
    }

}
