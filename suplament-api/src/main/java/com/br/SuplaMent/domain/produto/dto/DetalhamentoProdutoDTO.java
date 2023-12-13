package com.br.SuplaMent.domain.produto.dto;


import com.br.SuplaMent.domain.produto.Produto;

public record DetalhamentoProdutoDTO(Long id, String nome,String descricao, double valor, int qtd, String nomeImagem) {

    public DetalhamentoProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getValor(), (int) produto.getQtd(), produto.getNomeImagem());
    }


}
