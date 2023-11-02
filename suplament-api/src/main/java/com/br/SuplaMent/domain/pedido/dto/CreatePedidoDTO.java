package com.br.SuplaMent.domain.pedido.dto;

import com.br.SuplaMent.domain.produto.Produto;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public record CreatePedidoDTO(
        @NotNull
        Long idCliente,
        @NotNull
        List<Produto> produtos,
        @NotNull
        Double valorTotal,
        @NotBlank
        String enderecoEntrega,
        @NotBlank
        String formaPagamento,
        @NotNull
        Double valorFrete,
        @NotNull
        LocalDateTime dataPedido,
        @NotNull
        Date dataEntrega
) {
}
