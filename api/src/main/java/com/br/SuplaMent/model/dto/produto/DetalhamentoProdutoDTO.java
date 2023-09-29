package com.br.SuplaMent.model.dto.produto;


import com.br.SuplaMent.model.Produto;

public record DetalhamentoProdutoDTO(Long id, String nome, double avaliacao,String descri, double valor, int qtd, String nomeImagem) {

    public DetalhamentoProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getAvaliacao(), produto.getDescri(), produto.getValor(), (int) produto.getQtd(), produto.getNomeImagem());
    }


}
