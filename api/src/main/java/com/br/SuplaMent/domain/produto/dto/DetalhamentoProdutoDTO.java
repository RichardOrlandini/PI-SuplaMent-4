package com.br.SuplaMent.domain.produto.dto;


import com.br.SuplaMent.domain.produto.Produto;

public record DetalhamentoProdutoDTO(Long id, String nome, String descri, double valor, int qtd) {

    public DetalhamentoProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescri(), produto.getValor(), (int) produto.getQtd());
    }


}
