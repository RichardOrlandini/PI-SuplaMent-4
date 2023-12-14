package com.br.SuplaMent.domain.pedido.dto;

import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.domain.produto.dto.DetalhamentoProdutoDTO;

import java.time.LocalDateTime;
import java.util.List;

public record DetalhamentoPedidoDTO(List<DetalhamentoProdutoDTO> detalhamentoProdutoDTOS, double valorFrete, double valorTotal, String enderecoEntrega, String formaPagamento) {
    public DetalhamentoPedidoDTO(List<DetalhamentoProdutoDTO> detalhamentoProdutoDTOS, double valorFrete, double valorTotal, String enderecoEntrega, String formaPagamento) {
        this.detalhamentoProdutoDTOS = List.copyOf(detalhamentoProdutoDTOS);
        this.valorFrete = valorFrete;
        this.valorTotal = valorTotal;
        this.enderecoEntrega = enderecoEntrega;
        this.formaPagamento = formaPagamento;
    }
}
