package com.br.SuplaMent.domain.produto.dto;


import com.br.SuplaMent.domain.produto.Produto;

public record DetalhamentoProdutoDTO(Long id, String nome, double avaliacao,String descri, double valor, int qtd, String nomeImagem) {

//    public DetalhamentoProdutoDTO(Produto produto) {
//        this(produto.getId(), produto.getNome(), produto.getAvaliacao(), produto.getDescri(), produto.getValor(), (int) produto.getQtd(), produto.getNomeImagem());
//    }


}
