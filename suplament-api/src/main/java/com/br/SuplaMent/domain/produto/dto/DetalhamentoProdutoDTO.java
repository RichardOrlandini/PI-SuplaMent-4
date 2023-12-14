package com.br.SuplaMent.domain.produto.dto;


import com.br.SuplaMent.domain.pedido.dto.DetalhamentoPedidoDTO;
import com.br.SuplaMent.domain.produto.Produto;
import jakarta.validation.constraints.NotNull;

public record DetalhamentoProdutoDTO(Long id, String nome, double valorTotal, int qtdComprada) {

    public DetalhamentoProdutoDTO(Produto produto, double valorTotal, int qtdComprada) {
        this(produto.getId(), produto.getNome(), valorTotal, qtdComprada);
    }
}
