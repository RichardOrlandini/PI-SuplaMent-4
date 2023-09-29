package com.br.SuplaMent.model.dto.pedido;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreatePedidoDTO(
        Long idCliente,
        @NotNull
        Long idProduto,

        @NotNull
        @Future
        LocalDateTime data
) {
}
