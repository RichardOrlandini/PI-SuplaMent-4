package com.br.SuplaMent.domain.pedido.dto;

import com.br.SuplaMent.domain.pedido.Pedido;
import com.br.SuplaMent.utils.enums.StatusPedido;

import java.time.LocalDateTime;

public record ListagemPedidosDTO(Long id, LocalDateTime dataPedido, Double valor, StatusPedido statusPedido) {
    public ListagemPedidosDTO(Pedido pedido) {
        this(pedido.getId(), pedido.getDataPedido(), pedido.getValorTotal(), pedido.getStatusPedido());
    }
}
